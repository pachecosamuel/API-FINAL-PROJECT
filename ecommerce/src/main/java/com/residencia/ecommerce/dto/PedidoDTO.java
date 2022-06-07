package com.residencia.ecommerce.dto;

import java.time.LocalDate;
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
	private LocalDate dataPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataEntrega;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataEnvio;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Boolean status;

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

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
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

    public Boolean isStatus() {
        return status;
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
