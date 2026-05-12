package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
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
        this.quantity = quantity;
        this.description = description;
        this.discountPercentage = discountPercentage;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getNome();
        this.price = product.getPreco();
        this.quantity = product.getQuantidade();
        this.description = product.getDescricao();
        this.salePrice = product.getPrecoDeVenda();
        this.discountPercentage = product.getPorcentagemDesconto();
    }
}
