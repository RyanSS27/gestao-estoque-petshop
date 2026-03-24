package br.com.fishaquapets.petshop_api.model.entity;

import br.com.fishaquapets.petshop_api.model.enums.CategoriaServico;
import jakarta.persistence.*;
import jdk.jfr.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String nome;
    private BigDecimal precoBase;

    @Column(nullable = true)
    private String descricao;
    private List<CategoriaServico> categorias = new ArrayList<>();
}
