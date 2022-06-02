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

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProdutoService produtoService;

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
		itemPedido.getPedido().setIdPedido(itemPedidoDTO.getIdPedido());
		itemPedido.getProduto().setIdProduto(itemPedidoDTO.getIdPedido());
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
		itemPedidoDTO.setPedidoDTO(pedidoService.converterEntidadeParaDto(itemPedido.getPedido()));
		itemPedidoDTO.setProdutoDTO(produtoService.converterEntidadeParaDto(itemPedido.getProduto()));
		itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
		itemPedidoDTO.setQuantidadeProduto(itemPedido.getQuantidadeProduto());
		itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
		itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
		
		return itemPedidoDTO;
	}

}
