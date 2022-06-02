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
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAllProduto() {
		List<ProdutoDTO> produtoList = produtoService.findAllProduto();

		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer id) {

		ProdutoDTO produtoDTO = produtoService.findProdutoById(id);

		if (produtoDTO == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProduto(produtoDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProduto(produtoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProdutoById(@PathVariable Integer id) {

		ProdutoDTO produtoDTO = produtoService.findProdutoById(id);

		if (produtoDTO == null)
			throw new NoSuchElementFoundException(" " + id);
		else
			produtoService.deleteProdutoById(id);

		return new ResponseEntity<>("...", HttpStatus.OK);

	}

}
