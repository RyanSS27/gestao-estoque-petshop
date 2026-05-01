package com.fishaquapets.petshop_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fornecedores")
public class Supplier implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fornecedor")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String contato;

    @JsonIgnore
    @ManyToMany(mappedBy = "fornecedores")
    private Set<Product> products = new HashSet<>();

    public Supplier() {}

    public Supplier(Long id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Supplier that = (Supplier) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
