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

import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/item_pedido")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAllItemPedido() {
		List<ItemPedido> itemPedidoList = itemPedidoService.findAllItemPedido();

		return new ResponseEntity<>(itemPedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> findItemPedidoById(@PathVariable Integer id) {

		ItemPedido itemPedido = itemPedidoService.findItemPedidoById(id);

		if (itemPedido == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			return new ResponseEntity<>(itemPedido, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody ItemPedido itemPedido) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedido), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody ItemPedido itemPedido) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedido), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemPedidoById(@PathVariable Integer id) {

		ItemPedido itemPedido = itemPedidoService.findItemPedidoById(id);

		if (itemPedido == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			itemPedidoService.deleteItemPedidoById(id);

		return new ResponseEntity<>("ItemPedido deletado com sucesso", HttpStatus.OK);

	}

}
