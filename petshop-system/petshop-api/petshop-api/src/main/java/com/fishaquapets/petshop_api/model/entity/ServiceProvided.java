package com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "serviços_prestados")
public class ServiceProvided extends FinancialTransaction {
    @Column(name = "nome_servico")
    private String serviceName;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            name = "categorias_de_servico",
            joinColumns = @JoinColumn(name = "id_servico"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Category> categories = new HashSet<>();

    @Column
    private BigDecimal inputCosts;

    @Column(name = "data_servico")
    private Instant serviceDate;
}
