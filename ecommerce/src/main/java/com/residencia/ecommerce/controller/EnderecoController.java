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

import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.EnderecoService;



@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
		List<EnderecoDTO> enderecoList = enderecoService.findAllEndereco();

		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id) {

		EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);

		if (enderecoDTO == null) {
			throw new NoSuchElementFoundException("Não foi encontrado um Endereço com o id: " + id);
		} else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
		}

	}

	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> findEnderecoByCEP(@PathVariable String cep) {

		EnderecoDTO enderecoDTO = enderecoService.findEnderecoByCEP(cep);

		if (enderecoDTO == null) {
			throw new NoSuchElementFoundException("Não foi encontrado um Endereço com o CEP: " + cep);
		} else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		return new ResponseEntity<>(enderecoService.saveEndereco(enderecoDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		return new ResponseEntity<>(enderecoService.saveEndereco(enderecoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEnderecoById(@PathVariable Integer id) {

		EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);

		if (enderecoDTO == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereco deletado com sucesso.", HttpStatus.OK);

	}

}
