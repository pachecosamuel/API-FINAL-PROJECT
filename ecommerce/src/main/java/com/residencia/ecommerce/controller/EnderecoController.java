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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereço", description = "Endpoints")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	@Operation(summary = "Lista todos os endereços.")
	public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
		List<EnderecoDTO> enderecoList = enderecoService.findAllEndereco();

		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um endereço através de um id.")
	public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id) {

		EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);

		if (enderecoDTO == null) {
			throw new NoSuchElementFoundException("Não foi encontrado um Endereço com o id: " + id);
		} else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
		}

	}

	@GetMapping("/{cep}")
	@Operation(summary = "Lista um endereço forcendo um cep.")
	public ResponseEntity<List<EnderecoDTO>> findEnderecoByCep(@PathVariable String cep) {

		List<EnderecoDTO> enderecoDTOlist = enderecoService.findEnderecoByCep(cep);

		if (enderecoDTOlist == null) {
			throw new NoSuchElementFoundException("Não foi encontrado um Endereço com o CEP: " + cep);
		} else {
			return new ResponseEntity<>(enderecoDTOlist, HttpStatus.OK);
		}
	}

	@PostMapping("/query")
	@Operation(summary = "Cria um novo endereço fornecendo o CEP e número. (API - ViaCEP)")
	public ResponseEntity<EnderecoDTO> saveEnderecoViaCEP(@RequestParam String cep, @RequestParam Integer numero) {
		return new ResponseEntity<>(enderecoService.saveEnderecoViaCEP(cep, numero), HttpStatus.CREATED);
	}

	@PutMapping("/{id}/query")
	@Operation(summary = "Atualiza um endereço através de um CEP e um número. (API - ViaCEP)")
	public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable Integer id, @RequestParam String cep, @RequestParam Integer numero) {
		return new ResponseEntity<>(enderecoService.updateEnderecoViaCEP(id, cep, numero), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um endereço atráves de um id.")
	public ResponseEntity<String> deleteEnderecoById(@PathVariable Integer id) {

		EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);

		if (enderecoDTO == null)
			throw new NoSuchElementFoundException("Não foi encontrado um Endereço cadastrado com o id: " + id);
		else
			enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereco deletado com sucesso.", HttpStatus.OK);

	}

}
