package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private Instant updateDate;
    private String description;
    private BigDecimal discountPercentage;
    private BigDecimal salePrice;

    public ProductDTO() {}

    public ProductDTO(
            Long id,
            String name,
            BigDecimal price,
            int quantity,
            String description,
            BigDecimal discountPercentage
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.discountPercentage = discountPercentage;

        this.calculateSalePrice();
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getNome();
        this.price = product.getPreco();
        this.quantity = product.getQuantidade();
    }

    public void calculateSalePrice() {
        if (this.price != null && this.discountPercentage != null) {
            BigDecimal fatorDesconto = this.discountPercentage
                    .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);

            BigDecimal valorDesconto = this.price.multiply(fatorDesconto);

            this.salePrice = this.price.subtract(valorDesconto)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.salePrice = this.price; // Sem desconto, o preço de venda é o preço original
        }
    }
}
