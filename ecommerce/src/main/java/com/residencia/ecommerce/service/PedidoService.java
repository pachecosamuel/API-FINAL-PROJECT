package com.residencia.ecommerce.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
		newPedidoDTO.setStatus(false);

		return converterEntidadeParaDto(pedidoRepository.save(convertDTOToEntidade(newPedidoDTO)));
	}

	public PedidoDTO updatePedido(PedidoDTO pedidoDTO, Integer id) {
		pedidoDTO.setIdPedido(id);
		
		PedidoDTO pedidoAntigoDTO = findPedidoById(id);
		
		pedidoDTO.setDataPedido(pedidoAntigoDTO.getDataPedido());
		
		if (pedidoDTO.getIdCliente() == null) {
			pedidoDTO.setIdCliente(pedidoAntigoDTO.getClienteDTO().getIdCliente());
		}
		
		if (pedidoDTO.getDataPedido() == null) {
			pedidoDTO.setDataPedido(pedidoAntigoDTO.getDataPedido());
		}

		Pedido pedidoSalvo = pedidoRepository.save(convertDTOToEntidade(pedidoDTO));

		return findPedidoById(pedidoSalvo.getIdPedido());
	}

	public void deletePedidoById(Integer id) {
		PedidoDTO pedidoDTOAltering = findPedidoById(id);
		pedidoDTOAltering.setStatus(false);
		
		pedidoRepository.save(convertDTOToEntidade(pedidoDTOAltering));
	}

	public PedidoDTO pedidoIsActive(Integer id) throws MessagingException {
		PedidoDTO pedidoDTO = findPedidoById(id);
		pedidoDTO.setStatus(true);

		// Data de envio prevista dentro de 24 horas. (Placeholder)
		LocalDate envio = LocalDate.now();
		envio.plusDays(1);
		pedidoDTO.setDataEnvio(Date.from(envio.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		//Data de entrega prevista para dentro de 7 dias após envio. (Placeholder)
		LocalDate entrega = envio;
		entrega.plusDays(7);
		pedidoDTO.setDataEntrega(Date.from(entrega.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		emailService.sendEmailHTML("godbless@godbless.com", "Testando!", pedidoDTO);

		return updatePedido(pedidoDTO, id);
	}

	public Pedido convertDTOToEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();

		Cliente cliente = new Cliente();
		cliente.setIdCliente(pedidoDTO.getIdCliente());
		pedido.setCliente(cliente);

		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setStatus(pedidoDTO.getStatus());

		if (pedidoDTO.getIdPedido() == null) {
			pedido.setDataPedido(new Date());
		} else {
			pedidoDTO.setDataPedido(pedidoDTO.getDataPedido());
		}

		if(pedido.getDataPedido() == null) {
			throw new RuntimeException("Erro ao cadastrar data do pedido.");
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
		pedidoDTO.setStatus(pedido.getStatus());
		
		List<ItemPedidoDTO> listItemPedidoDTO = new ArrayList<ItemPedidoDTO>();
		for (ItemPedido itemPedido : pedido.getItemPedidoList()) {
			ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();

			itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
			itemPedidoDTO.setIdPedido(itemPedido.getPedido().getIdPedido());
			itemPedidoDTO.setProdutoDTO(produtoService.converterEntidadeParaDto(itemPedido.getProduto()));
			itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
			itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
			itemPedidoDTO.setQuantidadeProduto(itemPedido.getQuantidadeProduto());
			itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
			itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());

			listItemPedidoDTO.add(itemPedidoDTO);
		}

		pedidoDTO.setListItemPedidoDTO(listItemPedidoDTO);

		if (!pedido.getItemPedidoList().isEmpty()) {
			pedidoDTO.setValorTotal(calcService.calcValorTotal(pedido.getItemPedidoList()));
		}

		return pedidoDTO;
	}

}
