package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.OrderItem;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class OrderItemDTO {
    private Long productId;
    private String name;
    private int quantity;
    private BigDecimal price;
    private Integer discountPercentage;
    private BigDecimal discountValue; // Valor em R$ do desconto no item
    private BigDecimal subTotal; // Valor final do item (já com desconto)

    public OrderItemDTO() { }

    public OrderItemDTO(Long productId, String name, int quantity, BigDecimal price, Integer discountPercentage, BigDecimal subTotal) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.subTotal = subTotal;

        // Calcula o valor absoluto do desconto em Reais para enviar mastigado ao Front-end
        calcularValorDesconto();
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.productId = orderItem.getProductId();
        this.name = orderItem.getProductName();
        this.quantity = orderItem.getQuantidade();
        this.price = orderItem.getValorUnitario();
        this.discountPercentage = orderItem.getDiscountPercentage();
        this.subTotal = orderItem.getTotalItemValue();

        // Calcula o valor absoluto do desconto em Reais para enviar mastigado ao Front-end
        calcularValorDesconto();
    }

    private void calcularValorDesconto() {
        if (this.price != null && this.discountPercentage != null && this.discountPercentage > 0) {
            BigDecimal valorBruto = this.price.multiply(BigDecimal.valueOf(this.quantity));

            this.discountValue = valorBruto.multiply(BigDecimal.valueOf(this.discountPercentage))
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.discountValue = BigDecimal.ZERO;
        }
    }
}