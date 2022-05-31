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
@Table(name = "pedido")
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "idPedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;
	
	@Column(name = "", nullable = false)
	private Date dataPedido;
	
	@Column(name = "", nullable = false)
	private Date dataEntrega;
	
	@Column(name = "", nullable = false)
	private Date dataEnvio;
	
	@Column(name = "", nullable = false)
	private Boolean status;
	
	/* ID CLIENTE VIRÁ COMO FOREIGN KEY*/

}
