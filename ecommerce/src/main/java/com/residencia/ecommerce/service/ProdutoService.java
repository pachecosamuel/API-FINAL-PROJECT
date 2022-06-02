package com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.residencia.ecommerce.dto.ProdutoDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.AlreadyExistsException;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ImageFilesService filesService;

	public List<Produto> findAllProduto() {
		return produtoRepository.findAll();
	}

	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public Produto saveProduto(Produto produto) {
		for (Produto produtoExistente : findAllProduto()) {
			if (produtoExistente.getDescricaoProduto() == produto.getDescricaoProduto()) {
				throw new AlreadyExistsException("Já existe um Produto cadastrado com a descrição passada");
			}
		}
		return produtoRepository.save(produto);
	}

	public Produto saveProdutoWithImage(String produto, MultipartFile file) throws IOException {
		Produto newProduto = new Produto();

		try {
			ObjectMapper objMapper = new ObjectMapper();
			newProduto = objMapper.readValue(produto, Produto.class);
		} catch (IOException e) {
			throw new IOException("Erro de conversão da String para Entidade");
		}

		Produto produtoSaved = produtoRepository.save(newProduto);

		String fileName = "produto." + produtoSaved.getIdProduto() + ".image.png";

		filesService.saveFile(fileName, file);

		produtoSaved.setCaminhoImagem(filesService.getFilePathAsString(fileName));
		
		return produtoRepository.save(produtoSaved);
	}

	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deleteProdutoById(Integer id) {
		produtoRepository.deleteById(id);
	}
	
	private Produto convertDTOToEntidade(ProdutoDTO produtoDTO){
		Produto produto = new Produto();
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.getCategoria().setIdCategoria(produtoDTO.getCategoriaDTO().getIdCategoria());
		produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		produto.setDescricaoImagem(produtoDTO.getDescricaoImagem());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoque(produtoDTO.getQtdEstoque());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		return produto;
	}
		
	private ProdutoDTO converterEntidadeParaDto(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.getCategoriaDTO().setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setDescricaoImagem(produto.getDescricaoImagem());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		return produtoDTO;
	}
}
