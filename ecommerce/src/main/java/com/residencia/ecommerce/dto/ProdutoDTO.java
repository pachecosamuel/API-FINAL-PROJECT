package com.residencia.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idProduto;

	@NotBlank(message = "Nome do produto não informado.")
	@Size(max = 30, message = "O nome deverá ter no máximo 30 caracteres")
	private String nomeProduto;

	@NotBlank(message = "Descrição do produto não informado.")
	@Size(max = 100, message = "A descrição deverá ter no máximo 100 caracteres")
	private String descricaoProduto;

	@NotNull(message = "Quantidade em estoque não informada.")
	@Min(value = 0, message = "Estoque do produto não pode ser menor que 0")
	private Integer qtdEstoque;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataCadastroProduto;

	@NotNull(message = "Valor unitário não informado")
	@Min(value = 0, message = "Valor unitário não pode ser menor que 0")
	private Double valorUnitario;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String caminhoImagem;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private CategoriaDTO categoriaDTO;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "O id da Categoria não pode ser nulo.")
	private Integer idCategoria;

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

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}

	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

}
