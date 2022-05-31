package com.residencia.ecommerce.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "cliente")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@Column(name = "cpf", nullable = false, unique = true, length = 11)
	private String cpf;

	@Column(name = "telefone", nullable = false)
	private String telefone;

	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	/* ID_ENDERECO VIRÁ COMO FOREIGN KEY REFERENCE ENDERECO */
	@ManyToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	/* ID PEDIDO VIRÁ COMO FOREIGN KEY MANY TO MANY 	
	@ManyToMany
	@JoinTable(name = "cliente_pedido",
			joinColumns = @JoinColumn(name = "cliente_fk"),
			inverseJoinColumns = @JoinColumn(name = "pedido_fk"))
	List<Pedido> pedidoList; */
	


	

}
