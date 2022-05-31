package com.residencia.ecommerce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "produto")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "idProduto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_instrutor")
	private Integer idProduto;

	@Column(name = "nome", nullable = false)
	private String nomeProduto;
	
	@Column(name = "descricao", nullable = false, unique = true, length = 100)
	private String descricaoProduto;
	
	@Column(name = "qtd_estoque", nullable = false)
	private Integer qtdEstoque;
	
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastroProduto;
	
	@Column(name = "valor_unitario", nullable = false)
	private Float valorUnitario;
	
	@Column(name = "imagem", nullable = false)
	private String descricaoImagem;
	
	/* ID CATEGORIA VIRÁ COMO FOREIGN KEY */
	
}
