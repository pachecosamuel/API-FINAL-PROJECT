package com.residencia.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "item_pedido")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "idItemPedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Integer idItemPedido;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidadeProduto;
	
	@Column(name = "preco_venda", nullable = false)
	private Float precoVenda;
	
	@Column(name = "percentual_desconto", nullable = false)
	private Number percentualDesconto;
	
	@Column(name = "valor_bruto", nullable = false)
	private Float valorBruto;
	
	@Column(name = "valor_liquido", nullable = false)
	private Float valorLiquido;
	
	/* VIRÃO 2 FOREIGN KEYS UMA DE ID_PRODUTO E UMA DE ID_PEDIDO*/
	
	
	

}
