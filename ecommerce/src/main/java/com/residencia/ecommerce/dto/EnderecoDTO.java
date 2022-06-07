package com.residencia.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoDTO {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idEndereco;

	@NotBlank(message = "CEP não informado.")
	@Size(min = 8, max = 8, message = "CEP deve conter 8 caracteres (Sem traços, pontos, e barras)")
	private String cep;

	@NotBlank(message = "Rua não informada.")
	@Size(max = 100, message = "O nome da rua deverá ter no máximo 100 caracteres")
	private String rua;

	@NotBlank(message = "Bairro não informado.")
	@Size(max = 50, message = "O nome do bairro deverá ter no máximo 50 caracteres")
	private String bairro;

	@NotBlank(message = "Cidade não informada.")
	@Size(max = 30, message = "O nome da cidade deverá ter no máximo 30 caracteres")
	private String cidade;

	@NotNull(message = "Número não informado.")
	private Integer numeroEndereco;

	@NotBlank(message = "Complemento não informado.")
	@Size(max = 20, message = "O complemento deverá ter no máximo 20 caracteres")
	private String complemento;

	@NotBlank(message = "UF não informado.")
	@Size(min = 2, max = 2, message = "UF deve conter 2 caracteres")
	private String uf;

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
