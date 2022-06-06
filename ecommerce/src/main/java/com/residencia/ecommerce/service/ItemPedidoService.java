package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	CalcService calcService;

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

	public ItemPedidoDTO updateItemPedido(ItemPedidoDTO itemPedidoDTO, Integer id) {
		ItemPedido itemPedidoSalvo = itemPedidoRepository.save(convertDTOToEntidade(itemPedidoDTO));
	
		itemPedidoDTO.setIdItemPedido(id);
		
		ItemPedidoDTO itemPedidoAntigoDTO = findItemPedidoById(id);
		
		if (itemPedidoDTO.getIdPedido() == null) {
			itemPedidoDTO.setIdPedido(itemPedidoAntigoDTO.getIdPedido());
		}
		
		if (itemPedidoDTO.getIdProduto() == null) {
			itemPedidoDTO.setIdProduto(itemPedidoAntigoDTO.getProdutoDTO().getIdProduto());
		}
		
		if (itemPedidoDTO.getQuantidadeProduto() == null) {
			itemPedidoDTO.setQuantidadeProduto(itemPedidoAntigoDTO.getQuantidadeProduto());
		}
		
		if (itemPedidoDTO.getPrecoVenda() == null) {
			itemPedidoDTO.setPrecoVenda(itemPedidoAntigoDTO.getPrecoVenda());
		}
		
		
		return findItemPedidoById(itemPedidoSalvo.getIdItemPedido());
	}

	public void deleteItemPedidoById(Integer id) {
		itemPedidoRepository.deleteById(id);
	}

	private ItemPedido convertDTOToEntidade(ItemPedidoDTO itemPedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(itemPedidoDTO.getIdPedido());

		Produto produto = new Produto();
		produto.setIdProduto(itemPedidoDTO.getIdProduto());

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
		itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
		itemPedido.setQuantidadeProduto(itemPedidoDTO.getQuantidadeProduto());
		itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
		itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());

		ProdutoDTO produtoUpdated = produtoService.findProdutoById(itemPedidoDTO.getIdProduto());
		
		produtoUpdated.setQtdEstoque(produtoUpdated.getQtdEstoque() - itemPedido.getQuantidadeProduto());

		produtoService.updateProduto(produtoUpdated, produtoUpdated.getIdProduto());

		return itemPedido;
	}

	private ItemPedidoDTO converterEntidadeParaDto(ItemPedido itemPedido) {
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
		itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
		itemPedidoDTO.setIdPedido(itemPedido.getPedido().getIdPedido());
		itemPedidoDTO.setProdutoDTO(produtoService.converterEntidadeParaDto(itemPedido.getProduto()));
		itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
		itemPedidoDTO.setQuantidadeProduto(itemPedido.getQuantidadeProduto());
		itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
		itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
		
		return itemPedidoDTO;
	}

}
