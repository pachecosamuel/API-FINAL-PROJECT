package com.residencia.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProdutoDTO {

	private Integer idProduto;

	@NotBlank(message = "Nome do produto não informado.")
	private String nomeProduto;

	@NotBlank(message = "Descrição do produto não informado.")
	@Length(max = 100, message = "A descrição deverá ter no máximo {100} caracteres")
	private String descricaoProduto;

	@NotNull(message = "Quantidade em estoque não informada.")
	private Integer qtdEstoque;

	@JsonIgnore
	private Date dataCadastroProduto = new Date();

	@NotNull(message = "Valor unitário não informado")
	private Double valorUnitario;

	@NotBlank(message = "Descrição da imagem não informada.")
	@Length(max = 100, message = "A descrição deverá ter no máximo {100} caracteres")
	private String descricaoImagem;

	private CategoriaDTO categoriaDTO;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Date getDataCadastroProduto() {
		return dataCadastroProduto;
	}

	public void setDataCadastroProduto(Date dataCadastroProduto) {
		this.dataCadastroProduto = dataCadastroProduto;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getDescricaoImagem() {
		return descricaoImagem;
	}

	public void setDescricaoImagem(String descricaoImagem) {
		this.descricaoImagem = descricaoImagem;
	}

	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}

	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}

}
