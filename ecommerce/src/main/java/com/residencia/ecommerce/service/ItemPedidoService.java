package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public List<ItemPedido> findAllItemPedido() {
		return itemPedidoRepository.findAll();
	}

	public ItemPedido findItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).isPresent() ? itemPedidoRepository.findById(id).get() : null;
	}

	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	public ItemPedido updateItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	public void deleteItemPedidoById(Integer id) {
		itemPedidoRepository.deleteById(id);
	}
	
	private ItemPedido convertDTOToEntidade(ItemPedidoDTO itemPedidoDTO){
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
		itemPedido.getPedido().setIdPedido(itemPedidoDTO.getPedidoDTO().getIdPedido());
		itemPedido.getProduto().setIdProduto(itemPedidoDTO.getPedidoDTO().getIdPedido());
		itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
		itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
		itemPedido.setQuantidadeProduto(itemPedidoDTO.getQuantidadeProduto());
		itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
		itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());
		return itemPedido;
	}
		
	private ItemPedidoDTO converterEntidadeParaDto(ItemPedido itemPedido) {
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
		itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
		itemPedidoDTO.getPedidoDTO().setIdPedido(itemPedido.getPedido().getIdPedido());
		itemPedidoDTO.getProdutoDTO().setIdProduto(itemPedido.getPedido().getIdPedido());
		itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
		itemPedidoDTO.setQuantidadeProduto(itemPedido.getQuantidadeProduto());
		itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
		itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
		return itemPedidoDTO;
	}

}
