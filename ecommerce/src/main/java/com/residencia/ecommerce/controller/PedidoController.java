package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAllPedido() {
		List<PedidoDTO> pedidoList = pedidoService.findAllPedido();

		return new ResponseEntity<>(pedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> findPedidoById(@PathVariable Integer id) {

		PedidoDTO pedidoDTO = pedidoService.findPedidoById(id);

		if (pedidoDTO == null)
			throw new NoSuchElementFoundException("Não existe pedido especificado com o id: " + id);
		else
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<PedidoDTO> savePedido(@RequestBody PedidoDTO pedidoDTO) {
		return new ResponseEntity<>(pedidoService.savePedido(pedidoDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PedidoDTO> updatePedido(@RequestBody PedidoDTO pedidoDTO) {
		return new ResponseEntity<>(pedidoService.savePedido(pedidoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedidoById(@PathVariable Integer id) {

		PedidoDTO pedidoP = pedidoService.findPedidoById(id);

		if (pedidoP == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			pedidoService.deletePedidoById(id);

		return new ResponseEntity<>("Pedido deletado com sucesso.", HttpStatus.OK);

	}

}
