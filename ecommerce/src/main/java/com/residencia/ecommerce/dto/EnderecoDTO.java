package com.residencia.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EnderecoDTO {
	
	private Integer idEndereco;

	@NotBlank(message = "CEP não informado.")
	@Length(min = 8, max = 9, message = "Tamanho do CEP não corresponde ao esperado. 8 caracteres sem pontuação, 9 caracteres com pontuação")
	private String cep;

	@NotBlank(message = "Rua não informada.")
	private String rua;

	@NotBlank(message = "Bairro não informado.")
	private String bairro;

	@NotBlank(message = "Cidade não informada.")
	private String cidade;

	@NotNull(message = "Número não informado.")
	private Integer numeroEndereco;

	@NotBlank(message = "Complemento não informado.")
	private String complemento;

	@NotBlank(message = "UF não informado.")
	@Length(min = 2, max = 2, message = "Tamanho máximo excedido, informar UF com apenas duas letras.")
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
