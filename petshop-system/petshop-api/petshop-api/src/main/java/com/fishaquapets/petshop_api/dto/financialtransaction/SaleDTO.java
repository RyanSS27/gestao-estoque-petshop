package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.OrderItem;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SaleDTO {
    private Instant dateTime;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private BigDecimal totalValue;
    private BigDecimal payment;
    private Integer quantityOfItens;
    private Set<OrderItemDTO> orderItemsDTO = new HashSet<>();

    public SaleDTO() {}

    public SaleDTO(
            Instant dateTime,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus,
            BigDecimal totalValue,
            BigDecimal payment,
            Set<OrderItem> orderItems
    ) {
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.totalValue = totalValue;
        this.payment = payment;
        if (orderItems != null) {
            this.orderItemsDTO = orderItems.stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toSet());
        }
        this.quantityOfItens = this.orderItemsDTO.size();
    }

    public SaleDTO(Sale sale) {
        this.dateTime = sale.getRegistrationDateTime();
        this.paymentMethod = sale.getPaymentMethod();
        this.paymentStatus = sale.getPaymentStatus();
        this.totalValue = sale.getTotalValue();
        this.payment = sale.getPayment();
        if (sale.getItens() != null) {
            this.orderItemsDTO = sale.getItens().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toSet());
        }
        this.quantityOfItens = this.orderItemsDTO.size();
    }
}
