package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAllProduto() {
		return produtoRepository.findAll();
	}

	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
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
