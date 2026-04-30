package com.fishaquapets.petshop_api.model.pk;

import com.fishaquapets.petshop_api.model.entity.Produto;
import com.fishaquapets.petshop_api.model.entity.Venda;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class ItemPedidoPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return Objects.equals(venda, that.venda) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venda, produto);
    }
}

