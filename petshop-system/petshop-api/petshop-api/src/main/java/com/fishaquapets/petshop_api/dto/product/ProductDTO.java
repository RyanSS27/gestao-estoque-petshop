package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private int quantity;
    private int quantidadeMinimaIdeal;
    private Instant updateDate;
    private BigDecimal discountPercentage;
    private BigDecimal precoDeCusto;
    private BigDecimal precoDeVenda;
    private Set<CategoryDTO> categories;
    private Set<SupplierDTO> suppliers;

    public ProductDTO() {}

    public ProductDTO(
            Long id,
            String name,
            BigDecimal price,
            String description,
            int quantity,
            int minIdealQuantity,
            Instant updateDate,
            BigDecimal discountPercentage,
            BigDecimal costPrice,
            BigDecimal salePrice,
            Set<CategoryDTO> categories,
            Set<SupplierDTO> suppliers
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.quantidadeMinimaIdeal = minIdealQuantity;
        this.updateDate = updateDate;
        this.discountPercentage = discountPercentage;
        this.precoDeCusto = costPrice;
        this.precoDeVenda = salePrice;
        this.categories = categories;
        this.suppliers = suppliers;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.quantidadeMinimaIdeal = product.getMinIdealQuantity();
        this.updateDate = product.getUpdateDate();
        this.discountPercentage = product.getDiscountPercentage();
        this.precoDeCusto = product.getCostPrice();
        this.precoDeVenda = product.getSalePrice();

        // Conversão de categorias
        if (product.getCategories() != null) {
            this.categories = product.getCategories().stream()
                    .map(CategoryDTO::new)// Conversão
                    .collect(Collectors.toSet());
        }

        if (product.getSuppliers() != null) {
            this.suppliers = product.getSuppliers().stream()
                    .map(SupplierDTO::new) // Conversão de cada Supplier da entidade para SupplierDTO
                    .collect(Collectors.toSet());
        }
    }
}
