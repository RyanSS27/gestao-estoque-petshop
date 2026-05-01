package com.fishaquapets.petshop_api.model.entity;

import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendas")
public class Sale implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_venda")
    private Long id;

    @Column(name = "estado_pagamento", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "metodo_pagamento", nullable = false)
    private PaymentMethod paymentMethod;

    @Column
    private BigDecimal pagamento;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.venda")
    private Set<OrderItem> itens = new HashSet<>();

    @Setter(AccessLevel.NONE)
    private List<String> comentarios = new ArrayList<>();


    public Sale() {}

    public Sale(
            Long id, PaymentStatus paymentStatus,
            PaymentMethod paymentMethod, BigDecimal pagamento, List<String> comentarios
    ) {
        this.id = id;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.pagamento = pagamento;
        this.comentarios = comentarios;
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

    public void addComentario(String comentario) {
        this.comentarios.add(comentario);
    }
}
