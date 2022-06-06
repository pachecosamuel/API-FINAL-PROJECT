package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.ItemPedido;

@Service
public class CalcService {
    public Double calcValorLiquido(Double valor, Double desc) {
        return valor * (100 - desc) / 100;
    }

    public Double calcPrecoVenda(Double valor, Integer qtd, Double desc) {
        return calcValorLiquido(valor, desc) * qtd;
    }

    public Double calcValorTotal(List<ItemPedido> itemPedidoList) {
        Double total = 0.0;

        for (ItemPedido itemPedido : itemPedidoList) {
            total += itemPedido.getPrecoVenda();
        }
        return total;
    }
}
