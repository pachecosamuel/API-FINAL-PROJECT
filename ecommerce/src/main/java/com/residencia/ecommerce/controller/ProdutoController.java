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

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	@Operation(summary = "Lista todos os Produtos.")
	public ResponseEntity<List<ProdutoDTO>> findAllProduto() {
		List<ProdutoDTO> produtoList = produtoService.findAllProduto();

		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um Produto através de um id.")
	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer id) {

		ProdutoDTO produtoDTO = produtoService.findProdutoById(id);

		if (produtoDTO == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);

	}

	@PostMapping
	@Operation(summary = "Cria um novo Produto.")
	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProduto(produtoDTO), HttpStatus.CREATED);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um Produto.")
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.updateProduto(produtoDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um Produto através de um id.")
	public ResponseEntity<String> deleteProdutoById(@PathVariable Integer id) {

		ProdutoDTO produtoDTO = produtoService.findProdutoById(id);

		if (produtoDTO == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			produtoService.deleteProdutoById(id);

		return new ResponseEntity<>("Produto deletado com sucesso.", HttpStatus.OK);

	}

}
