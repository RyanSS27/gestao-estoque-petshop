package com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.Instant;

@Table(name = "serviços_prestados")
public class ServiceProvided extends FinancialTransaction {

    @Column(name = "data_servico")
    private Instant serviceDate;
}
