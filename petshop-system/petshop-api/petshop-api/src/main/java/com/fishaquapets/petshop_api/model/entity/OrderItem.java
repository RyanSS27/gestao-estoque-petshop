package com.fishaquapets.petshop_api.model.entity;


import com.fishaquapets.petshop_api.model.pk.OrderItemPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "itens_pedido")
public class OrderItem {
    @Setter(AccessLevel.NONE)
    @EmbeddedId // id integrado
    private OrderItemPK id;

    @Column(nullable = false)
    private Integer quantidade;


    @Column(name = "preco_unitario")
    private BigDecimal valorUnitario;

    public OrderItem() {}

    public OrderItem(Sale sale, Product product, Integer quantidade) {
        this.id = new OrderItemPK(sale, product);
        this.quantidade = quantidade;
        this.valorUnitario = product.getPrecoDeVenda();
    }

    public BigDecimal getSubTotal() {
        if (valorUnitario == null || quantidade == null)
            return BigDecimal.ZERO;

        return valorUnitario.multiply(new BigDecimal(quantidade));
    }

    public Sale getVenda() {
        return id.getSale();
    }

    public Product getProduto() {
        return id.getProduct();
    }

    public Long getSaleId() {
        return this.id.getSaleId();
    }

    public Long getProductId() {
        return this.id.getProductId();
    }

    public String getProductName() { return id.getProduct().getNome(); }

    public void atualizarQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /*
        Por enquanto, não utilizados (passíveis de remoção):

        public void adicionar(int quantidade) {
            this.quantidade += quantidade;
        }

        public void retirara(int quantidade) {
            this.quantidade += quantidade;
        }
    */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
