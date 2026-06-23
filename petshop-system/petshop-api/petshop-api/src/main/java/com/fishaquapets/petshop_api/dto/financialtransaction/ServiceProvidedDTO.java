package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.entity.ServiceProvided;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.SaleType;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ServiceProvidedDTO {
    private Long id;
    private BigDecimal value;
    private String serviceName;
    private Set<Category> categories = new HashSet<>();
    private Instant serviceDate;

    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    private BigDecimal subTotal;
    private Integer discountPercentage;
    private BigDecimal discountValue; // Valor calculado em R$ do desconto
    private BigDecimal totalValue;
    private BigDecimal payment;


    public ServiceProvidedDTO(Long id, BigDecimal value, String serviceName, Set<Category> categories, Instant serviceDate, PaymentMethod paymentMethod, PaymentStatus paymentStatus, BigDecimal subTotal, Integer discountPercentage, BigDecimal discountValue, BigDecimal totalValue, BigDecimal payment) {
        this.id = id;
        this.value = value;
        this.serviceName = serviceName;
        this.categories = categories;
        this.serviceDate = serviceDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.discountValue = discountValue;
        this.totalValue = totalValue;
        this.payment = payment;
    }

    public ServiceProvidedDTO(ServiceProvided service) {
        this.id = service.getId();
        this.value = service.getTotalValue();
        this.serviceName = service.getServiceName();
        this.categories = service.getCategories();
        this.serviceDate = service.getServiceDate();
        this.paymentMethod = service.getPaymentMethod();
        this.paymentStatus = service.getPaymentStatus();
        this.subTotal = service.getSubTotal();
        this.discountPercentage = service.getDiscountPercentage();
        this.totalValue = service.getTotalValue();
        this.payment = service.getPayment();

        // Calcula o valor absoluto do desconto
        if (this.subTotal != null && this.discountPercentage != null && this.discountPercentage > 0) {
            this.discountValue = this.subTotal.multiply(BigDecimal.valueOf(this.discountPercentage))
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.discountValue = BigDecimal.ZERO;
        }
    }
}
