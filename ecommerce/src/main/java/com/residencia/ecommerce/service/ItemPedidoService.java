package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
