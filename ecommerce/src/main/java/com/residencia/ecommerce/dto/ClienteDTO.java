package com.residencia.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteDTO {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idCliente;

	@Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail não informado")
	@Size(max = 100, message = "O email deverá ter no máximo 100 caracteres")
	private String email;

	@NotBlank(message = "Nome não informado")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Primeira letra do nome deve ser maiúscula")
	@Size(max = 100, message = "O nome deverá ter no máximo 100 caracteres")
	private String nomeCompleto;

	@CPF(message = "Número CPF inválido")
    @NotBlank(message = "CPF não informado")
	@Size(min = 14, max = 14, message = "O CPF deverá ter 14 caracteres (Sem traços, pontos, e barras)")
	private String cpf;

	@NotBlank(message = "Telefone não informado")
	@Size(min = 10, max = 11, message = "O telefone deverá ter entre 10 e 11 caracteres (Incluindo DDD, sem traços, pontos, barras, espaços em branco)")
	private String telefone;

	@NotNull(message = "Data de nascimento não informada. (Formatação: DD-MM-YYYY)")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private EnderecoDTO enderecoDTO;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "O CEP não pode ser nulo.")
	private String cepEndereco;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "O Número do Endereço não pode ser nulo.")
	private Integer numeroEndereco;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String complemento;

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

    public String getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(String cepEndereco) {
        this.cepEndereco = cepEndereco;
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

}
