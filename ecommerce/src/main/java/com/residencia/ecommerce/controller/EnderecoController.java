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

import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAllEndereco() {
		List<Endereco> enderecoList = enderecoService.findAllEndereco();

		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findEnderecoById(@PathVariable Integer id) {

		Endereco endereco = enderecoService.findEnderecoById(id);

		if (endereco == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			return new ResponseEntity<>(endereco, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEnderecoById(@PathVariable Integer id) {

		Endereco endereco = enderecoService.findEnderecoById(id);

		if (endereco == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereco deletado com sucesso.", HttpStatus.OK);

	}

}
