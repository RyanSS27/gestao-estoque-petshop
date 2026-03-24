package br.com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String contato;
}
