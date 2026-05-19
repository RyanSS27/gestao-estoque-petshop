package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.OrderItem;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderItemDTO {
    private Long productId;
    private String name;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

    public OrderItemDTO() { }


    public OrderItemDTO(Long productId, String name, int quantity, BigDecimal price, BigDecimal subTotal) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.productId = orderItem.getProductId();
        this.name = orderItem.getProductName();
        this.quantity = orderItem.getQuantidade();
        this.price = orderItem.getValorUnitario();
        this.subTotal = orderItem.getSubTotal();
    }
}
