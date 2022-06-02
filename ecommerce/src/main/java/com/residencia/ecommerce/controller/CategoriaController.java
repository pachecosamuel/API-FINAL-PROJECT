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

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAllCategoria() {
		List<CategoriaDTO> categoriaList = categoriaService.findAllCategoria();

		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> findCategoriaById(@PathVariable Integer id) {

		CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);

		if (categoriaDTO == null) 
			throw new NoSuchElementFoundException("Não existe categoria cadastrada com o id: " + id);
		else
			return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoriaDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<CategoriaDTO> updateCategoria(@RequestBody CategoriaDTO categoriaDTO) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoriaDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoriaById(@RequestBody Integer id) {

		CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);

		if (categoriaDTO == null)
			throw new NoSuchElementFoundException("Não existe categoria cadastrado com o id: " + id);
		else
			categoriaService.deleteCategoriaById(id);
		return new ResponseEntity<>("Categoria deletada com sucesso.", HttpStatus.OK);

	}

}
