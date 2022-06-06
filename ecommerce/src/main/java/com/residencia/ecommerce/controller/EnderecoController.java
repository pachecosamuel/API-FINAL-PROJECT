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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/endereco")
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

	@PostMapping
	@Operation(summary = "Cria um novo endereço.")
	public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		return new ResponseEntity<>(enderecoService.saveEndereco(enderecoDTO), HttpStatus.CREATED);
	}

	@PostMapping("/query")
	@Operation(summary = "Cria um novo endereço fornecendo o CEP e número. (API - ViaCEP)")
	public ResponseEntity<EnderecoDTO> saveEnderecoViaCEP(@RequestParam String cep, @RequestParam Integer numero) {
		return new ResponseEntity<>(enderecoService.saveEnderecoViaCEP(cep, numero), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um endereço.")
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.updateEndereco(enderecoDTO, id), HttpStatus.OK);
	}

	@PutMapping("/viacep/{id}")
	@Operation(summary = "Atualiza um endereço através de um CEP e um número.")
	public ResponseEntity<EnderecoDTO> updateEnderecoViaCEP(@PathVariable Integer id, @RequestParam String cep, @RequestParam Integer numero) {
		return new ResponseEntity<>(enderecoService.updateEnderecoViaCEP(id, cep, numero), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um endereço atráves de um id.")
	public ResponseEntity<String> deleteEnderecoById(@PathVariable Integer id) {

		EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);

		if (enderecoDTO == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereco deletado com sucesso.", HttpStatus.OK);

	}

}
