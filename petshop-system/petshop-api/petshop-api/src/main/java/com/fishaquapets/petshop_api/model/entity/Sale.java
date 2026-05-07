package com.fishaquapets.petshop_api.model.entity;

import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
            // Diz que, se o item for removido a Venda (ficar órfão),
            // ele deve ser apagado do banco - programadores e suas nomenclaturas kkkkkkkkkk
    )
    private Set<OrderItem> itens = new HashSet<>();



    public Sale() {}

    public Sale(
            Long id, PaymentStatus paymentStatus,
            PaymentMethod paymentMethod, BigDecimal pagamento, List<String> comentarios
    ) {
        super(id, paymentStatus, paymentMethod, pagamento, comentarios); // ajustar comentários

        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PrePersist
    @PreUpdate
    private void atualizarTotal() {
        calcularValorTotal();
    }

    // Falta criar os métodos de adição/subtração de itens
    public void addItem(Product product, Integer quantidade) {
        this.itens.add(new OrderItem(this, product, quantidade));
    }

    public void removeItem(OrderItem orderItem) {
        this.itens.remove(orderItem);
    }
}
