package com.residencia.ecommerce.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "produto")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProduto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
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
	
	/* id_produto irá como FK para ItemPedido*/
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> ItemPedidoList;

	
	/* ID CATEGORIA VIRÁ COMO FOREIGN KEY
	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	private Categoria Categoria; */


	
	

}
