package com.residencia.ecommerce.service;

import java.util.ArrayList;
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

	public List<ItemPedidoDTO> findAllItemPedido() {

		List<ItemPedidoDTO> listItemPedidoDTO = new ArrayList<>();

		for (ItemPedido itemPedido : itemPedidoRepository.findAll()) {
			listItemPedidoDTO.add(converterEntidadeParaDto(itemPedido));
		}

		return listItemPedidoDTO;
	}

	public ItemPedidoDTO findItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).isPresent() ? converterEntidadeParaDto(itemPedidoRepository.findById(id).get()) : null;
	}

	public ItemPedidoDTO saveItemPedido(ItemPedidoDTO itemPedidoDTO) {
		
		ItemPedido itemPedidoSalvo = itemPedidoRepository.save(convertDTOToEntidade(itemPedidoDTO));
	
		return findItemPedidoById(itemPedidoSalvo.getIdItemPedido());
	}

	public ItemPedidoDTO updateItemPedido(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedidoSalvo = itemPedidoRepository.save(convertDTOToEntidade(itemPedidoDTO));
	
		return findItemPedidoById(itemPedidoSalvo.getIdItemPedido());
	}

	public void deleteItemPedidoById(Integer id) {
		itemPedidoRepository.deleteById(id);
	}

	private ItemPedido convertDTOToEntidade(ItemPedidoDTO itemPedidoDTO) {
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
