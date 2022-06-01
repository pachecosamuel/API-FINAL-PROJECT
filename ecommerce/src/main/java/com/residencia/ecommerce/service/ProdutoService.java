package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.AlreadyExistsException;
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
		for (Produto produtoExistente : findAllProduto()) {
			if (produtoExistente.getDescricaoProduto() == produto.getDescricaoProduto()) {
				throw new AlreadyExistsException("Já existe um Produto cadastrado com a descrição passada");
			}
		}
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deleteProdutoById(Integer id) {
		produtoRepository.deleteById(id);
	}
}
