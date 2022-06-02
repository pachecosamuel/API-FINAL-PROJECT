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

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/item_pedido")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedidoDTO>> findAllItemPedido() {
		List<ItemPedidoDTO> itemPedidoList = itemPedidoService.findAllItemPedido();

		return new ResponseEntity<>(itemPedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoDTO> findItemPedidoById(@PathVariable Integer id) {

		ItemPedidoDTO itemPedidoDTO = itemPedidoService.findItemPedidoById(id);

		if (itemPedidoDTO == null)
			throw new NoSuchElementFoundException("Não existe um ItemPedido cadastrado com o id" + id);
		else
			return new ResponseEntity<>(itemPedidoDTO, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<ItemPedidoDTO> saveItemPedido(@RequestBody ItemPedidoDTO itemPedidoDTO) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedidoDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ItemPedidoDTO> updateItemPedido(@RequestBody ItemPedidoDTO itemPedidoDTO) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedidoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemPedidoById(@PathVariable Integer id) {

		ItemPedidoDTO itemPedidoDTO = itemPedidoService.findItemPedidoById(id);

		if (itemPedidoDTO == null)
			throw new NoSuchElementFoundException("Não existe ItemPedido com o id informado." + id);
		else
			itemPedidoService.deleteItemPedidoById(id);

		return new ResponseEntity<>("ItemPedido deletado com sucesso", HttpStatus.OK);

	}

}
