package com.residencia.ecommerce.controller;

import java.util.List;

import javax.mail.MessagingException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Endpoints")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	@Operation(summary = "Lista todos os Pedidos.")
	public ResponseEntity<List<PedidoDTO>> findAllPedido() {
		List<PedidoDTO> pedidoList = pedidoService.findAllPedido();

		return new ResponseEntity<>(pedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um Pedido através de um id.")
	public ResponseEntity<PedidoDTO> findPedidoById(@PathVariable Integer id) {

		PedidoDTO pedidoDTO = pedidoService.findPedidoById(id);

		if (pedidoDTO == null)
			throw new NoSuchElementFoundException("Não existe pedido especificado com o id: " + id);
		else
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);

	}

	@PostMapping("/query")
	@Operation(summary = "Cria um novo Pedido.")
	public ResponseEntity<PedidoDTO> savePedido(@RequestParam Integer cliente) {
		return new ResponseEntity<>(pedidoService.savePedido(cliente), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um Pedido.")
	public ResponseEntity<PedidoDTO> updatePedido(@RequestBody PedidoDTO pedidoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(pedidoService.updatePedido(pedidoDTO, id), HttpStatus.OK);

	}

	@PutMapping("/{id}/active")
	@Operation(summary = "Declara um pedido como ativo e preparado para envio, gerando data estimada de envio e entrega automaticamente.")
	public ResponseEntity<PedidoDTO> activePedido(@PathVariable Integer id) throws MessagingException {
		return new ResponseEntity<>(pedidoService.pedidoIsActive(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um pedido através do seu ID. (Normalmente, não se deleta pedidos. Recomendado usar o Put de Inativação.)")
	public ResponseEntity<String> deletePedido(@PathVariable Integer id) {
		
		PedidoDTO pedidoDTO = pedidoService.findPedidoById(id);

		if (pedidoDTO == null)
			throw new NoSuchElementFoundException("Não existe pedido especificado com o id: " + id);
		else

		return new ResponseEntity<>("Pedido deletado com sucesso.", HttpStatus.OK);
	}

	@PutMapping("/{id}/inactive")
	@Operation(summary = "Declara como entregue um Pedido através de um id.")
	public ResponseEntity<String> deletePedidoById(@PathVariable Integer id) {

		PedidoDTO pedidoDTO = pedidoService.findPedidoById(id);

		if (pedidoDTO == null)
			throw new NoSuchElementFoundException("Não foi encontrado um pedido com o id: " + id);
		else
			pedidoService.deletePedidoById(id);

		return new ResponseEntity<>("Pedido deletado com sucesso.", HttpStatus.OK);

	}

}
