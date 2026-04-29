package com.fishaquapets.petshop_api.model.entity;

import com.fishaquapets.petshop_api.model.enums.EstadoPagamento;
import com.fishaquapets.petshop_api.model.enums.MetodoPagamento;
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
public class Venda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "estado_pagamento", nullable = false)
    private EstadoPagamento estadoPagamento;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @Column
    private BigDecimal pagamento;

    @OneToMany(mappedBy = "id.venda")
    private Set<ItemPedido> itens = new HashSet<>();

    @Setter(AccessLevel.NONE)
    private List<String> comentarios = new ArrayList<>();


    public Venda() {}

    public Venda(Long id, EstadoPagamento estadoPagamento, BigDecimal valorTotal, MetodoPagamento metodoPagamento, BigDecimal pagamento, Set<ItemPedido> itens, List<String> comentarios) {
        this.id = id;
        this.estadoPagamento = estadoPagamento;
        this.valorTotal = valorTotal;
        this.metodoPagamento = metodoPagamento;
        this.pagamento = pagamento;
        this.itens = itens;
        this.comentarios = comentarios;
    }
}
