package com.residencia.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoDTO {

	private Integer idPedido;

	// Feito automaticamente pelo Service -- Mateus
	private Date dataPedido;

	@NotNull(message = "Data de entrega não informada. (Formatação: DD-MM-YYYY)")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataEntrega;

	@NotNull(message = "Data de envio não informado. (Formatação: DD-MM-YYYY)")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataEnvio;

	// Feito automaticamente pelo Service -- Mateus
	private Boolean status;

	// Feito automaticamente pelo Service -- Mateus
	private ClienteDTO clienteDTO;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

}
