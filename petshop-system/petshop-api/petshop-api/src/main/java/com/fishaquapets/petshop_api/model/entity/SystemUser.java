package com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class SystemUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "usuario", nullable = false)
    private String name;

    @Column(name = "empresa", nullable = false)
    private String company;

    @Column(name = "telefone", length = 11)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String senha; // procurar como criptografar futuramente

    public SystemUser() {}

    public SystemUser(Long id, String name, String company, String phoneNumber, String email, String senha) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
