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

import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAllCliente() {
		List<Cliente> clienteList = clienteService.findAllCliente();

		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {

		Cliente cliente = clienteService.findClienteById(id);

		if (cliente == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			return new ResponseEntity<>(cliente, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClienteById(@PathVariable Integer id) {

		Cliente cliente = clienteService.findClienteById(id);

		if (cliente == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			clienteService.deleteClienteById(id);

		return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);

	}

}
