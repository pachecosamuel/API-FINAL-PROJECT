package com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.AlreadyExistsException;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ImageFilesService filesService;

	public List<ProdutoDTO> findAllProduto() {
		List<ProdutoDTO> listProdutoDTO = new ArrayList<ProdutoDTO>();

		for(Produto produtoEntity : produtoRepository.findAll()){
			listProdutoDTO.add(converterEntidadeParaDto(produtoEntity));
		}

		return listProdutoDTO;
	}

	public ProdutoDTO findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? converterEntidadeParaDto(produtoRepository.findById(id).get())  : null;
	}

	public ProdutoDTO saveProduto(ProdutoDTO produtoDTO) {
		for (ProdutoDTO produtoExistente : findAllProduto()) {
			if (produtoExistente.getDescricaoProduto() == produtoDTO.getDescricaoProduto()) {
				throw new AlreadyExistsException("Já existe um Produto cadastrado com a descrição passada");
			}
		}

		Produto produtoSalvo = produtoRepository.save(convertDTOToEntidade(produtoDTO));

		return findProdutoById(produtoSalvo.getIdProduto());
	}

	public ProdutoDTO updateProduto(ProdutoDTO produtoDTO) {
		for (ProdutoDTO produtoExistente : findAllProduto()) {
			if (produtoExistente.getDescricaoProduto() == produtoDTO.getDescricaoProduto()) {
				throw new AlreadyExistsException("Já existe um Produto cadastrado com a descrição passada");
			}
		}

		Produto produtoSalvo = produtoRepository.save(convertDTOToEntidade(produtoDTO));

		return findProdutoById(produtoSalvo.getIdProduto());
	}

	public ProdutoDTO saveProdutoWithImage(String produtoDTO, MultipartFile file) throws IOException {
		ProdutoDTO newProdutoDTO = new ProdutoDTO();

		try {
			ObjectMapper objMapper = new ObjectMapper();
			newProdutoDTO = objMapper.readValue(produtoDTO, ProdutoDTO.class);
		} catch (IOException e) {
			throw new IOException("Erro de conversão da String para Entidade");
		}

		Produto produtoSaved = produtoRepository.save(convertDTOToEntidade(newProdutoDTO));

		String fileName = "produto." + produtoSaved.getIdProduto() + ".image.png";

		filesService.saveFile(fileName, file);

		produtoSaved.setCaminhoImagem(filesService.getFilePathAsString(fileName));
		
		produtoRepository.save(produtoSaved);

		return converterEntidadeParaDto(produtoSaved);
	}

	public void deleteProdutoById(Integer id) {
		produtoRepository.deleteById(id);
	}
	
	public Produto convertDTOToEntidade(ProdutoDTO produtoDTO){
		Produto produto = new Produto();
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.getCategoria().setIdCategoria(produtoDTO.getCategoriaDTO().getIdCategoria());
		produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		produto.setCaminhoImagem(produtoDTO.getCaminhoImagem());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoque(produtoDTO.getQtdEstoque());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		return produto;
	}
		
	public ProdutoDTO converterEntidadeParaDto(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.getCategoriaDTO().setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setCaminhoImagem(produto.getCaminhoImagem());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		return produtoDTO;
	}
}
