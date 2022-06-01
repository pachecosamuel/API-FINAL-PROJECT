package com.residencia.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {
	
	private Integer idCliente;

	@Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail não informado")
	private String email;

	@NotBlank(message = "Nome não informado")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Primeira letra do nome deve ser maiúscula")
	private String nomeCompleto;

	@CPF(message = "Número CPF inválido")
    @NotBlank(message = "CPF não informado")
	private String cpf;

	@NotBlank(message = "Telefone não informado")
	private String telefone;

	@NotNull(message = "Data de nascimento não informada.")
	private Date dataNascimento;

	private EnderecoDTO enderecoDTO;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

}
