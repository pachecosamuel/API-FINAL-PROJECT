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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "Endpoints")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	@Operation(summary = "Lista todas as categorias.")
	public ResponseEntity<List<CategoriaDTO>> findAllCategoria() {
		List<CategoriaDTO> categoriaList = categoriaService.findAllCategoria();

		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista uma categoria atráves de um id.")
	public ResponseEntity<CategoriaDTO> findCategoriaById(@PathVariable Integer id) {

		CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);

		if (categoriaDTO == null) 
			throw new NoSuchElementFoundException("Não existe categoria cadastrada com o id: " + id);
		else
			return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);

	}

	@PostMapping
	@Operation(summary = "Cria uma nova categoria.")
	public ResponseEntity<CategoriaDTO> saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoriaDTO), HttpStatus.CREATED);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Atualiza uma nova categoria.")
	public ResponseEntity<CategoriaDTO> updateCategoria(@RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(categoriaService.updateCategoria(categoriaDTO, id), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma categoria atráves de um id.")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer id) {

		CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);

		if (categoriaDTO == null)
			throw new NoSuchElementFoundException("Não existe categoria cadastrado com o id: " + id);
		else
			categoriaService.deleteCategoriaById(id);
		return new ResponseEntity<>("Categoria deletada com sucesso.", HttpStatus.OK);

	}

}
