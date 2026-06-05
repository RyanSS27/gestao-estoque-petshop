package com.fishaquapets.petshop_api.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "transacoes")
@Inheritance(strategy = InheritanceType.JOINED) // Diz que há a relação de herança
/*
    Pede para que se crie a tabela transações e que ela terá
    tabelas filhas. Ligando-as por id.

    Diz que haverá uma relação de Herança para com as tabelas
    de Venda e Produto.
*/
@Getter
@Setter
public abstract class FinancialTransaction implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "data_registro", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    private Instant registrationDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pagamento", nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "valor_total", nullable = false)
    protected BigDecimal totalValue = BigDecimal.ZERO;

    @Column(name = "desconto_porcentagem", nullable = true)
    private Integer discountPercentage;

    // Adicionei agora, complete a classe:
    @Column(name = "sub_total", nullable = true)
    private BigDecimal subTotal = BigDecimal.ZERO; // sem os descontos

    // preciso adicionar o subtotal da venda sem descontos

    @Column
    private BigDecimal payment;

    @Setter(AccessLevel.NONE)
    @ElementCollection // faz com que crie uma tabela auxiliar para guardar os comentários
    @CollectionTable(
            name = "comentarios_transacao", // nomeia a tabela
            joinColumns = @JoinColumn(name = "id_transaction")
    )
    @Column(name = "comentario")
    private List<String> comments = new ArrayList<>();

    public FinancialTransaction() {}

    public FinancialTransaction(
            Long id,
            Instant dateTime,
            PaymentStatus paymentStatus,
            PaymentMethod paymentMethod,
            BigDecimal payment,
            List<String> comments
    ) {
        this.id = id;
        this.registrationDateTime = dateTime;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.payment = payment;
        if (comments != null) {
            this.comments.addAll(comments);
        }
    }

    public void addComentario(String comentario) {
        this.comments.add(comentario);
    }

    // Retorna uma view imutável protegendo a referência original (Effective Java)
    public List<String> getComentarios() {
        return Collections.unmodifiableList(comments);
    }
}