package br.com.fishaquapets.petshop_api.model.entity;

import br.com.fishaquapets.petshop_api.model.enums.NivelAcesso;
import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;
    private String email;

    @Column(nullable = false)
    private String senha; // hash

    @Column(name = "nivel_acesso", nullable = false)
    private NivelAcesso nivelAcesso;
}
