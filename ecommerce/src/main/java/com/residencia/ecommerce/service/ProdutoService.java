package com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.AlreadyExistsException;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	ImageFilesService filesService;

	public List<ProdutoDTO> findAllProduto() {
		List<ProdutoDTO> listProdutoDTO = new ArrayList<ProdutoDTO>();

		for (Produto produtoEntity : produtoRepository.findAll()) {
			listProdutoDTO.add(converterEntidadeParaDto(produtoEntity));
		}

		return listProdutoDTO;
	}

	public ProdutoDTO findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent()
				? converterEntidadeParaDto(produtoRepository.findById(id).get())
				: null;
	}

	public Resource getFileFromProdutoById(Integer id) throws IOException {
		String fileName = "produto." + findProdutoById(id).getIdProduto() + ".image.png";

		return filesService.getFile(fileName);
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

	public ProdutoDTO updateProduto(ProdutoDTO produtoDTO, Integer id) {
		for (ProdutoDTO produtoExistente : findAllProduto()) {
			if (produtoExistente.getDescricaoProduto() == produtoDTO.getDescricaoProduto() && produtoExistente.getIdProduto() != produtoDTO.getIdProduto()) {
				throw new AlreadyExistsException("Já existe um Produto cadastrado com a descrição passada");
			}
		}

		produtoDTO.setIdProduto(id);
		
		ProdutoDTO produtoAntigoDTO = findProdutoById(id);

		if (produtoDTO.getDataCadastroProduto() == null) {
			produtoDTO.setDataCadastroProduto(produtoAntigoDTO.getDataCadastroProduto());
		}
		
		if (produtoDTO.getIdCategoria() == null) {
			produtoDTO.setIdCategoria(produtoAntigoDTO.getCategoriaDTO().getIdCategoria());
		}

		if (produtoDTO.getNomeProduto() == null) {
			produtoDTO.setNomeProduto(produtoAntigoDTO.getNomeProduto());
		}

		if (produtoDTO.getQtdEstoque() == null) {
			produtoDTO.setQtdEstoque(produtoAntigoDTO.getQtdEstoque());
		}

		if (produtoDTO.getValorUnitario() == null) {
			produtoDTO.setValorUnitario(produtoAntigoDTO.getValorUnitario());
		}

		if (produtoDTO.getCaminhoImagem() == null) {
			produtoDTO.setCaminhoImagem(produtoAntigoDTO.getCaminhoImagem());
		}

		if (produtoDTO.getDescricaoProduto() == null) {
			produtoDTO.setDescricaoProduto(produtoAntigoDTO.getDescricaoProduto());
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

	public ProdutoDTO updateProdutoImage(Integer id, MultipartFile file) throws IOException {
		ProdutoDTO produtoAltering = findProdutoById(id);

		String fileName = "produto." + produtoAltering.getIdProduto() + ".image.png";

		filesService.saveFile(fileName, file);

		produtoAltering.setCaminhoImagem(filesService.getFilePathAsString(fileName));
		produtoAltering.setIdCategoria(produtoAltering.getCategoriaDTO().getIdCategoria());

		Produto produtoAltered = produtoRepository.save(convertDTOToEntidade(produtoAltering));

		return converterEntidadeParaDto(produtoAltered);
	}

	public void deleteProdutoById(Integer id) {
		produtoRepository.deleteById(id);
	}

	public Produto convertDTOToEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		Categoria categoria = new Categoria();

		categoria.setIdCategoria(produtoDTO.getIdCategoria());

		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setCategoria(categoria);
		produto.setCaminhoImagem(produtoDTO.getCaminhoImagem());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoque(produtoDTO.getQtdEstoque());
		produto.setValorUnitario(produtoDTO.getValorUnitario());

		if (produto.getIdProduto() == null) {
			produto.setDataCadastroProduto(new Date());
		} else {
			produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		}

		if (produto.getDataCadastroProduto() == null) {
			throw new RuntimeException("Erro ao cadastrar data do produto.");
		}

		return produto;

	}

	public ProdutoDTO converterEntidadeParaDto(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setCategoriaDTO(categoriaService.converterEntidadeParaDto(produto.getCategoria()));
		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setCaminhoImagem(produto.getCaminhoImagem());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		produtoDTO.setValorUnitario(produto.getValorUnitario());

		return produtoDTO;
	}
}
