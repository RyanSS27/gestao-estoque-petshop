package br.com.fishaquapets.petshop_api.model.entity;

import java.math.BigDecimal;

// Configurar a questão de notação das tabelas em Spring com esta relação de herança
public class ItemVenda extends Item {
    /*
        Item venda será uma cópia de item com as informações
        necessárias para ser armazenadas na venda para evitar
        mutabilidade dos dados
    */
    public BigDecimal gerarSubTotal() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
