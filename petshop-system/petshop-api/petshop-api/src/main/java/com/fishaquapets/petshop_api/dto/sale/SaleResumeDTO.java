package com.fishaquapets.petshop_api.dto.sale;

import com.fishaquapets.petshop_api.model.entity.OrderItem;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.repository.ProductRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;

public class SaleResumeDTO {
    @Autowired
    private ProductRepository productRepository;
    private Instant dateTime;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private BigDecimal totalValue;
    private Integer quantityOfItens;
    private String itemName;


    public SaleResumeDTO() {}

    public SaleResumeDTO(Instant dateTime, PaymentMethod paymentMethod, PaymentStatus paymentStatus, BigDecimal totalValue, Integer quantityOfItens, String itemName) {
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.totalValue = totalValue;
        this.quantityOfItens = quantityOfItens;
        this.itemName = itemName;
    }

    public SaleResumeDTO(Sale sale) {
        this.dateTime = sale.getRegistrationDateTime();
        this.paymentMethod = sale.getPaymentMethod();
        this.paymentStatus = sale.getPaymentStatus();
        this.totalValue = sale.getTotalValue();
        this.quantityOfItens = sale.getItens().size();
        OrderItem item = sale.getItens().stream().findFirst().orElse(null);
        if (item != null) {
            Product product = item.getId().getProduct();
            this.itemName = product.getNome();
        } else {
            this.itemName = null;
        }
    }
}
