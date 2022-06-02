package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

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

	public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
		Pedido pedidoSalvo = pedidoRepository.save(convertDTOToEntidade(pedidoDTO));

		return findPedidoById(pedidoSalvo.getIdPedido());
	}

	public PedidoDTO updatePedido(PedidoDTO pedidoDTO) {
		Pedido pedidoSalvo = pedidoRepository.save(convertDTOToEntidade(pedidoDTO));

		return findPedidoById(pedidoSalvo.getIdPedido());
	}

	public void deletePedidoById(Integer id) {
		findPedidoById(id).setStatus(false);
		
		// Ao invés de deletar um Pedido apenas irá mudar para Inativo.
		// pedidoRepository.deleteById(id);
	}

	private Pedido convertDTOToEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.getCliente().setIdCliente(pedidoDTO.getClienteDTO().getIdCliente());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataPedido(pedidoDTO.getDataPedido());

		return pedido;
	}

	private PedidoDTO converterEntidadeParaDto(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.getClienteDTO().setIdCliente(pedido.getCliente().getIdCliente());
		pedidoDTO.setDataEntrega(pedido.getDataEntrega());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		return pedidoDTO;
	}

}
