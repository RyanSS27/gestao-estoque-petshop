package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class SaleResumeDTO {
    private Instant dateTime;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private BigDecimal totalValue;
    private Integer quantityOfItens;
    private String firstItemName;


    public SaleResumeDTO() {}

    public SaleResumeDTO(
            Instant dateTime,
            PaymentStatus paymentStatus,
            BigDecimal totalValue,
            Integer quantityOfItens,
            String firstItemName) {

        this.dateTime = dateTime;
        this.paymentStatus = paymentStatus;
        this.totalValue = totalValue;
        this.quantityOfItens = quantityOfItens;
        this.firstItemName = firstItemName;
    }

    public SaleResumeDTO(Sale sale) {
        this.dateTime = sale.getRegistrationDateTime();
        this.paymentStatus = sale.getPaymentStatus();
        this.totalValue = sale.getTotalValue();
        this.firstItemName = sale.getFistItemName();
        this.quantityOfItens = sale.getItens().size();
    }
}
