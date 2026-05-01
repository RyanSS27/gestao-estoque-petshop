package com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
    private int quantidade;

    @Column(name = "porcentagem_desconto")
    private BigDecimal porcentagemDesconto; // 0-100

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
    private Set<Category> categories = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            // Tabela de associação entre produtos e fornecedores
            name = "produto_fornecedor", // nome da nova tabela
            joinColumns = @JoinColumn(name = "id_produto"), // nome da coluna
            inverseJoinColumns = @JoinColumn(name = "id_fornecedor") // nome da coluna
    )
    private Set<Supplier> suppliers = new HashSet<>();

    public Product() {}

    public Product(Long id, String nome, BigDecimal preco, String descricao, BigDecimal porcentagemDesconto) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.porcentagemDesconto = porcentagemDesconto;

        this.calcularPrecoDeVenda();
    }


    public void addCategoria(Category category) {
        categories.add(category);
    }

    public void removeCategoria(Long id) {
        categories.removeIf(x -> x.getId().equals(id));
    }

    public void addFornecedor(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void removeFornecedor(Long id) {
        suppliers.removeIf(x -> x.getId().equals(id));
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

    public boolean vender(int qtd) {
        // deixei caso venha a se inserir alguma regra de negócio
        return reduzirEstoque(qtd);
    }

    public void adicionarEstoque(int quantidade) {
        if(quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser positiva");

        this.quantidade += quantidade;
    }

    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= 0) return false;
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
