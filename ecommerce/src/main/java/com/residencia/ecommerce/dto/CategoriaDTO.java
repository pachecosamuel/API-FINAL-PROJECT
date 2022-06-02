package com.residencia.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idCategoria;

	@NotBlank(message = "Nome categoria não informado.")
	private String nomeCategoria;

	@NotBlank(message = "Descrição da categoria não informada.")
	@Length(max = 150, message = "A descrição deverá ter no máximo {150} caracteres")
	private String categoriaDescricao;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}

	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}

}
