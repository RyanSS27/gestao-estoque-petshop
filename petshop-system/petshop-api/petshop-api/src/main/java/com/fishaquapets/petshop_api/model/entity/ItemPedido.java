package com.fishaquapets.petshop_api.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fishaquapets.petshop_api.model.pk.ItemPedidoPK;
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
public class ItemPedido {
    @Setter(AccessLevel.NONE)
    @EmbeddedId // id integrado
    private ItemPedidoPK id = new ItemPedidoPK();

    @Column
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    public BigDecimal getSubTotal() {
        if (valorUnitario == null || quantidade == null)
            return BigDecimal.ZERO;

        return valorUnitario.multiply(new BigDecimal(quantidade));
    }

    @JsonIgnore // evita o looping de chamados durante a serialização do Json
    public Venda getVenda() {
        return id.getVenda();
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
