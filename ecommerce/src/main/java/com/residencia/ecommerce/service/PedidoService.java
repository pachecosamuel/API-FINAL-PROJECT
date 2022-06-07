package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.repository.PedidoRepository;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ItemPedidoService itemPedidoService;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	CalcService calcService;

	@Autowired
	EmailService emailService;

	public List<PedidoDTO> findAllPedido() {
		List<PedidoDTO> listPedidoDTO = new ArrayList<>();

		for(Pedido pedidoEntity : pedidoRepository.findAll()){

			listPedidoDTO.add(converterEntidadeParaDto(pedidoEntity));

		}

		return listPedidoDTO;
	}

	public PedidoDTO findPedidoById(Integer id) {
		return pedidoRepository.findById(id).isPresent() ? converterEntidadeParaDto(pedidoRepository.findById(id).get()) : null;
	}

	public PedidoDTO savePedido(Integer id) {
		PedidoDTO newPedidoDTO = new PedidoDTO();
		newPedidoDTO.setIdCliente(id);
		newPedidoDTO.setStatus("INATIVO");

		return converterEntidadeParaDto(pedidoRepository.save(convertDTOToEntidade(newPedidoDTO)));
	}

	public PedidoDTO updatePedido(PedidoDTO pedidoDTO, Integer id) {
		pedidoDTO.setIdPedido(id);
		
		PedidoDTO pedidoAntigoDTO = findPedidoById(id);
		
		if (pedidoDTO.getDataPedido() == null) {
			pedidoDTO.setDataPedido(pedidoAntigoDTO.getDataPedido());
		}
		
		if (pedidoDTO.getIdCliente() == null) {
			pedidoDTO.setIdCliente(pedidoAntigoDTO.getClienteDTO().getIdCliente());
		}

		if (pedidoDTO.getDataEnvio() == null) {
			pedidoDTO.setDataEnvio(pedidoAntigoDTO.getDataEnvio());
		}

		if (pedidoDTO.getDataEntrega() == null) {
			pedidoDTO.setDataEntrega(pedidoAntigoDTO.getDataEntrega());
		}

		if (pedidoDTO.getStatus() == null) {
			pedidoDTO.setStatus(pedidoAntigoDTO.getStatus());
		}

		Pedido pedidoSalvo = pedidoRepository.save(convertDTOToEntidade(pedidoDTO));

		return findPedidoById(pedidoSalvo.getIdPedido());
	}

	public void deletePedidoById(Integer id) {
		pedidoRepository.deleteById(id);
	}

	public void pedidoIsInactive(Integer id) {
		PedidoDTO pedidoDTOAltering = findPedidoById(id);
		pedidoDTOAltering.setStatus("INATIVO");
		
		pedidoRepository.save(convertDTOToEntidade(pedidoDTOAltering));
	}

	public PedidoDTO pedidoIsActive(Integer id) throws MessagingException {
		if (findPedidoById(id) == null) {
			throw new NoSuchElementFoundException("Não há pedido com o id: " + id);
		}

		PedidoDTO pedidoDTO = findPedidoById(id);
		pedidoDTO.setStatus("ATIVO");

		// Data de envio prevista dentro de 24 horas. (Placeholder)
		Date d1 = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d1);
		c.add(Calendar.DATE, 1);
		Date envio = c.getTime();
		pedidoDTO.setDataEnvio(envio);

		//Data de entrega prevista para dentro de 7 dias após envio. (Placeholder)
		Date d2 = envio;
		c.setTime(d2);
		c.add(Calendar.DATE, 7);
		Date entrega = c.getTime();
		pedidoDTO.setDataEntrega(entrega);

		emailService.sendEmailHTML("nerdsincapi@gmail.com", "Novo Pedido Cadastrado | ID: " + pedidoDTO.getIdPedido(), pedidoDTO);

		return updatePedido(pedidoDTO, id);
	}

	public Pedido convertDTOToEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		
		Cliente cliente = new Cliente();

		if (pedidoDTO.getClienteDTO() == null) {
			cliente.setIdCliente(pedidoDTO.getIdCliente());
		} else {
			cliente.setIdCliente(pedidoDTO.getClienteDTO().getIdCliente());
		}

		pedido.setCliente(cliente);

		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());

		if (pedidoDTO.getStatus().equals("ATIVO")) {
			pedido.setStatus(true);
		} else if (pedidoDTO.getStatus().equals("INATIVO")) {
			pedido.setStatus(false);
		}

		if (pedidoDTO.getIdPedido() == null) {
			pedido.setDataPedido(new Date());
		} else {
			pedido.setDataPedido(pedidoDTO.getDataPedido());
		}

		if (pedido.getDataPedido() == null) {
			throw new RuntimeException("Erro ao cadastrar data do pedido.");
		}

		if (pedidoDTO.getListItemPedidoDTO() == null) {
			pedidoDTO.setListItemPedidoDTO(new ArrayList<ItemPedidoDTO>());
		}

		if (!pedidoDTO.getListItemPedidoDTO().isEmpty()) {
			List<ItemPedido> listItemPedido = new ArrayList<ItemPedido>();
			for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getListItemPedidoDTO()) {
				listItemPedido.add(itemPedidoService.convertDTOToEntidade(itemPedidoDTO));
			}
			pedido.setItemPedidoList(listItemPedido);
		}
		
		return pedido;
	}

	public PedidoDTO converterEntidadeParaDto(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setClienteDTO(clienteService.findClienteById(pedido.getCliente().getIdCliente()));
		pedidoDTO.setDataEntrega(pedido.getDataEntrega());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataPedido(pedido.getDataPedido());

		if (pedido.getStatus() == true) {
			pedidoDTO.setStatus("ATIVO");
		} else {
			pedidoDTO.setStatus("INATIVO");
		}
		
		if (pedido.getItemPedidoList() == null) {
			pedido.setItemPedidoList(new ArrayList<ItemPedido>());
		}

		List<ItemPedidoDTO> listItemPedidoDTO = new ArrayList<ItemPedidoDTO>();
		for (ItemPedido itemPedido : pedido.getItemPedidoList()) {
			ItemPedidoDTO itemPedidoDTO = itemPedidoService.converterEntidadeParaDto(itemPedido);
			listItemPedidoDTO.add(itemPedidoDTO);
		}

		pedidoDTO.setListItemPedidoDTO(listItemPedidoDTO);

		if (!pedido.getItemPedidoList().isEmpty()) {
			pedidoDTO.setValorTotal(calcService.calcValorTotal(pedido.getItemPedidoList()));
		}

		return pedidoDTO;
	}

}
