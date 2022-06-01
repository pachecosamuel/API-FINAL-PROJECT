package com.residencia.ecommerce.dto;

import javax.validation.constraints.NotNull;

public class ItemPedidoDTO {
	private Integer idItemPedido;

	@NotNull(message = "Quantidade do produto não informado.")
	private Integer quantidadeProduto;

	@NotNull(message = "Preço de venda não informado.")
	private Float precoVenda;

	@NotNull(message = "Percentual de desconto não informado.")
	private Float percentualDesconto;

	@NotNull(message = "Valor bruto não informado.")
	private Float valorBruto;

	@NotNull(message = "Valor líquido não informado.")
	private Float valorLiquido;

	private ProdutoDTO produtoDTO;

	private PedidoDTO pedidoDTO;

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

	public Float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Float getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Float percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Float getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Float valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Float getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Float valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public ProdutoDTO getProdutoDTO() {
		return produtoDTO;
	}

	public void setProdutoDTO(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

}
