package com.fishaquapets.petshop_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.SaleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendas")
public class Sale extends FinancialTransaction {
    @Setter(AccessLevel.NONE)
    @OneToMany(
            mappedBy = "id.sale",
            cascade = CascadeType.ALL, // Diz para o JPA que, se salvar Venda, salve os Itens também
            orphanRemoval = true
            // Diz que, se o item for removido da Venda (ficar órfão),
            // ele deve ser apagado do banco - programadores e suas nomenclaturas kkkkkkkkkk
    )
    private Set<OrderItem> itens = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_venda", nullable = false)
    private SaleType saleType;

    @Column(name = "frete", nullable = true)
    private BigDecimal freight;

    public Sale() {}

    public Sale(
            Long id,
            Instant dateTime,
            PaymentStatus paymentStatus,
            PaymentMethod paymentMethod,
            BigDecimal pagamento,
            List<String> comentarios,
            SaleType saleType
    ) {
        super(id, dateTime, paymentStatus, paymentMethod, pagamento, comentarios); // ajustar comentários
        this.saleType = saleType;
        calculateTotalValue();
    }

    public void calculateTotalValue() {
        BigDecimal totalItens = itens.stream()
                .map(OrderItem::getTotalItemValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Define o subtotal (soma dos itens com seus próprios descontos, mas sem o desconto da venda e sem frete)
        this.setSubTotal(totalItens.setScale(2, RoundingMode.HALF_UP));

        // Verifica se existe desconto a ser aplicado na venda inteira
        if (getDiscountPercentage() != null && getDiscountPercentage() > 0) {

            BigDecimal descontoPercentual = BigDecimal.valueOf(getDiscountPercentage())
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

            BigDecimal discountValue = totalItens.multiply(descontoPercentual);

            // Subtrai o desconto do total dos itens
            totalItens = totalItens.subtract(discountValue);
        }

        // Verifica se tem frete
        if (freight != null && freight.compareTo(BigDecimal.ZERO) > 0)
            totalItens = totalItens.add(freight);

        this.setTotalValue(totalItens.setScale(2, RoundingMode.HALF_UP));
    }

    @PrePersist
    @PreUpdate
    private void updateValues() {
        calculateTotalValue();
    }

    // Falta criar os métodos de adição/subtração de itens
    public void addItem(Product product, Integer quantidade, Integer discountPercentage) {
        this.itens.add(new OrderItem(this, product, quantidade, discountPercentage));
        calculateTotalValue(); // Recalcula totais ao adicionar item
    }

    public void removeItem(OrderItem orderItem) {
        this.itens.remove(orderItem);
        calculateTotalValue(); // Recalcula totais ao remover item
    }

    @JsonIgnore
    public String getFistItemName() {
        if (itens.isEmpty()) return "Produto não identificado";
        var item = itens.iterator().next();
        return item != null ? item.getProductName(): "Produto não identificado";
    }
}
