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

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Endpoints")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	@Operation(summary = "Lista todos os clientes.")
	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		List<ClienteDTO> clienteList = clienteService.findAllCliente();

		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um cliente atráves de um id.")
	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer id) {

		ClienteDTO clienteDTO = clienteService.findClienteById(id);

		if (clienteDTO == null)
			throw new NoSuchElementFoundException("Não existe cliente cadastrado com o id: " + id);
		else
			return new ResponseEntity<>(clienteDTO, HttpStatus.OK);

	}

	@PostMapping
	@Operation(summary = "Cria um novo cliente.")
	public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO) {
		return new ResponseEntity<>(clienteService.saveCliente(clienteDTO), HttpStatus.CREATED);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um cliente.")
	public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(clienteService.updateCliente(clienteDTO, id), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma cliente atráves de um id.")
	public ResponseEntity<String> deleteClienteById(@PathVariable Integer id) {

		ClienteDTO clienteDTO = clienteService.findClienteById(id);

		if (clienteDTO == null)
			throw new NoSuchElementFoundException("Não existe cliente cadastrado com o id: " + id);
		else
			clienteService.deleteClienteById(id);

		return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);

	}

}
