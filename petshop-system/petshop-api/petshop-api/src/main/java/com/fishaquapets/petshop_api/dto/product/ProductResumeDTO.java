package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResumeDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;


    public ProductResumeDTO() {
    }

    public ProductResumeDTO(Long id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductResumeDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
