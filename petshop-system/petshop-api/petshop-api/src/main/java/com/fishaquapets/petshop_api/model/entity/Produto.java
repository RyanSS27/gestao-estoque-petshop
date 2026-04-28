package com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {
    private static final long serialVersionID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_produto")
    private Long id;

    @Column(nullable = false)
    private String nome;
    private BigDecimal preco;

    @Column
    private String descricao;
    private BigDecimal porcentagemDesconto; // 0-100
    // ainda farei os métodos de calculo do desconto

    @Column(name = "preco_de_venda")
    private BigDecimal precoDeVenda;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            // Tabela de associação entre os IDs
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            // Tabela de associação entre produtos e fornecedores
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_fornecedor")
    )
    private Set<Fornecedor> fornecedores = new HashSet<>();

    public Produto() {}

    public Produto(Long id, String nome, BigDecimal preco, String descricao, BigDecimal porcentagemDesconto) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.porcentagemDesconto = porcentagemDesconto;

        this.calcularPrecoDeVenda();
    }


    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void removeCategoria(Long id) {
        categorias.removeIf(x -> x.getId().equals(id));
    }

    public void addFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public void removeFornecedor(Long id) {
        fornecedores.removeIf(x -> x.getId().equals(id));
    }

    public void calcularPrecoDeVenda() {
        if (this.preco != null && this.porcentagemDesconto != null) {
            BigDecimal fatorDesconto = this.porcentagemDesconto
                    .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);

            BigDecimal valorDesconto = this.preco.multiply(fatorDesconto);

            this.precoDeVenda = this.preco.subtract(valorDesconto)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.precoDeVenda = this.preco; // Sem desconto, o preço de venda é o preço original
        }
    }

    // Gatilhos do JPA para garantir que o cálculo ocorra antes de salvar no banco
    @PrePersist // executa antes do primeiro Insert
    @PreUpdate // executa antes do Update
    private void prepararDados() {
        calcularPrecoDeVenda();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
