package com.residencia.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class PedidoDTO {

	private Integer idPedido;

	@NotNull(message = "Data do pedido não informado.")
	private Date dataPedido;

	@NotNull(message = "Data de entrega não informada.")
	private Date dataEntrega;

	@NotNull(message = "Data de envio não informado.")
	private Date dataEnvio;

	@NotNull(message = "Status do produto não informado.")
	private Boolean status;

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
