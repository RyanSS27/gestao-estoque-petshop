package com.fishaquapets.petshop_api.model.entity;

import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "servicos_prestados")
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

    @Column(name = "custo_dos_insumos", nullable = true)
    private BigDecimal inputCosts;

    @Column(name = "data_servico", nullable = false)
    private Instant serviceDate;

    protected ServiceProvided() {
        super();
    }

    public ServiceProvided(
            Instant registrationDateTime,
            PaymentStatus paymentStatus,
            PaymentMethod paymentMethod,
            BigDecimal payment,
            List<String> comments,

            String serviceName,
            Set<Category> categories,
            BigDecimal inputCosts,
            Instant serviceDate
    ) {
        super(null, registrationDateTime, paymentStatus, paymentMethod, payment, comments);

        this.serviceName = serviceName;
        this.inputCosts = inputCosts;
        this.serviceDate = serviceDate;

        if (categories != null) {
            this.categories.addAll(categories);
        }
    }
}
