package com.fishaquapets.petshop_api.model.pk;

import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.model.entity.Sale;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class OrderItemPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Product product;

    public OrderItemPK() {}

    public OrderItemPK(Sale sale, Product product) {
        this.sale = sale;
        this.product = product;
    }

    public Long getProductId() {
        return product.getId();
    }

    public Long getSaleId() {
        return sale.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(sale, that.sale) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sale, product);
    }
}
