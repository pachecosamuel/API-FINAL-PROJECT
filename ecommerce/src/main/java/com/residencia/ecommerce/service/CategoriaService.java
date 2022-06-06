package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.exception.AlreadyExistsException;
import com.residencia.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> findAllCategoria() {
		List<CategoriaDTO> listCategoriaDTO = new ArrayList<CategoriaDTO>();

		for (Categoria categoriaEntity : categoriaRepository.findAll()) {
			listCategoriaDTO.add(converterEntidadeParaDto(categoriaEntity));
		}

		return listCategoriaDTO;
	}

	public CategoriaDTO findCategoriaById(Integer id) {
		return categoriaRepository.findById(id).isPresent()
				? converterEntidadeParaDto(categoriaRepository.findById(id).get())
				: null;
	}

	public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
		for (CategoriaDTO categoriaExistente : findAllCategoria()) {
			if (categoriaExistente.getCategoriaDescricao() == categoriaDTO.getCategoriaDescricao()) {
				throw new AlreadyExistsException("Já existe uma Categoria cadastrada com a descrição passada");
			}
		}

		Categoria categoriaSalvo = categoriaRepository.save(convertDTOToEntidade(categoriaDTO));

		return findCategoriaById(categoriaSalvo.getIdCategoria());
	}

	public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO, Integer id) {
		for (CategoriaDTO categoriaExistente : findAllCategoria()) {
			if (categoriaExistente.getCategoriaDescricao() == categoriaDTO.getCategoriaDescricao()) {
				throw new AlreadyExistsException("Já existe um categoria cadastrada com a descrição passada");
			}
		}

		categoriaDTO.setIdCategoria(id);

		CategoriaDTO categoriaAntigaDTO = findCategoriaById(id);

		if (categoriaDTO.getNomeCategoria() == null) {
			categoriaDTO.setNomeCategoria(categoriaAntigaDTO.getNomeCategoria());

		}

		Categoria categoriaSalvo = categoriaRepository.save(convertDTOToEntidade(categoriaDTO));

		return findCategoriaById(categoriaSalvo.getIdCategoria());
	}

	public void deleteCategoriaById(Integer id) {
		categoriaRepository.deleteById(id);
	}

	public Categoria convertDTOToEntidade(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		categoria.setCategoriaDescricao(categoriaDTO.getCategoriaDescricao());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
		return categoria;
	}

	public CategoriaDTO converterEntidadeParaDto(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setCategoriaDescricao(categoria.getCategoriaDescricao());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
		return categoriaDTO;
	}

}
