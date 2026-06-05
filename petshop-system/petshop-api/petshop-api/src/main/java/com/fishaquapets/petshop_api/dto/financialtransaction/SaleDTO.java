package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.OrderItem;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.SaleType;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SaleDTO {
    private Long id;
    private Instant dateTime;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private SaleType saleType;

    private BigDecimal subTotal;
    private Integer discountPercentage;
    private BigDecimal discountValue; // Valor calculado em R$ do desconto
    private BigDecimal freight;
    private BigDecimal totalValue;
    private BigDecimal payment;

    private Set<OrderItemDTO> orderItems = new HashSet<>();

    public SaleDTO() {}

    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.dateTime = sale.getRegistrationDateTime();
        this.paymentMethod = sale.getPaymentMethod();
        this.paymentStatus = sale.getPaymentStatus();
        this.saleType = sale.getSaleType();
        this.payment = sale.getPayment();
        this.totalValue = sale.getTotalValue();
        this.subTotal = sale.getSubTotal();
        this.discountPercentage = sale.getDiscountPercentage();
        this.freight = sale.getFreight();

        // Calcula o valor absoluto do desconto
        if (this.subTotal != null && this.discountPercentage != null && this.discountPercentage > 0) {
            this.discountValue = this.subTotal.multiply(BigDecimal.valueOf(this.discountPercentage))
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.discountValue = BigDecimal.ZERO;
        }

        if (sale.getItens() != null) {
            this.orderItems = sale.getItens().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toSet());
        }
    }

    public SaleDTO(
            Long id,
            Instant dateTime,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus,
            BigDecimal subTotal,
            Integer discountPercentage,
            BigDecimal freight,
            BigDecimal totalValue,
            BigDecimal payment,
            Set<OrderItem> orderItems,
            SaleType saleType
    ) {
        this.id = id;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.freight = freight;
        this.totalValue = totalValue;
        this.payment = payment;
        this.saleType = saleType;

        if (this.subTotal != null && this.discountPercentage != null && this.discountPercentage > 0) {
            this.discountValue = this.subTotal.multiply(BigDecimal.valueOf(this.discountPercentage))
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.discountValue = BigDecimal.ZERO;
        }

        if (orderItems != null) {
            this.orderItems = orderItems.stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toSet());
        }
    }
}