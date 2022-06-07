package com.residencia.ecommerce.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataEntrega;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataEnvio;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String status;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Double valorTotal;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ClienteDTO clienteDTO;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedidoDTO> listItemPedidoDTO;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "O id do Cliente não pode ser nulo.")
	private Integer idCliente;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoDTO> getListItemPedidoDTO() {
        return listItemPedidoDTO;
    }

    public void setListItemPedidoDTO(List<ItemPedidoDTO> listItemPedidoDTO) {
        this.listItemPedidoDTO = listItemPedidoDTO;
    }

}
