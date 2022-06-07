package com.residencia.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Endpoints")
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
	
	@GetMapping("/{id}/image")
	@Operation(summary = "Baixa a imagem de um produto.")
	public ResponseEntity<Resource> getProdutoImageById(@PathVariable Integer id) throws IOException {
		//Define header para a resposta da API para poder gerar um "link" para baixar a imagem
		HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.png");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
		
		//Usa-se outro método de ResponseEntity para poder usar as funções .headers, .contentLength, .contentType, e .body
		return ResponseEntity.ok()
							 .headers(header)
							 .contentLength(new File(findProdutoById(id).getBody().getCaminhoImagem()).length())
							 .contentType(MediaType.APPLICATION_OCTET_STREAM)
							 .body(produtoService.getFileFromProdutoById(id));
	}

	@PostMapping
	@Operation(summary = "Cria um novo Produto.")
	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProduto(produtoDTO), HttpStatus.CREATED);
	}

	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.MULTIPART_FORM_DATA_VALUE })
	@Operation(summary = "Cria um Produto com foto.")
	public ResponseEntity<ProdutoDTO> saveProdutoWithImage(@RequestPart("produto") String produto,
		@RequestPart("file") MultipartFile file) throws IOException {
	return new ResponseEntity<>(produtoService.saveProdutoWithImage(produto, file), HttpStatus.CREATED);
}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um Produto através do ID.")
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.updateProduto(produtoDTO, id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}/update-image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	@Operation(summary = "Atualiza a imagem de um Produto através de seu ID.")
	public ResponseEntity<ProdutoDTO> updateProdutoImage(@PathVariable Integer id, @RequestPart("file") MultipartFile file) throws IOException {
		return new ResponseEntity<>(produtoService.updateProdutoImage(id, file), HttpStatus.OK);
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
