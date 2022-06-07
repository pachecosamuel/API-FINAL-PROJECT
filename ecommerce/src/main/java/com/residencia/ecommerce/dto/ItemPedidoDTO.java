package com.residencia.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemPedidoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idItemPedido;

	@NotNull(message = "Quantidade do produto não informado.")
	@Min(value = 0, message = "Não pode pedir itens em quantidade negativa.")
	private Integer quantidadeProduto;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Double precoVenda;

	@NotNull(message = "Percentual de desconto não informado.")
	private Double percentualDesconto;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Double valorBruto;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Double valorLiquido;

	@NotNull(message = "O id do Pedido não pode ser nulo")
	private Integer idPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ProdutoDTO produtoDTO;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "O id do Produto não pode ser nulo.")
	private Integer idProduto;

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public ProdutoDTO getProdutoDTO() {
		return produtoDTO;
	}

	public void setProdutoDTO(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

}
