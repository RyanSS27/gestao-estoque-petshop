package com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
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
    private String name;
    private BigDecimal price;

    @Column
    private String description;
    private int quantity;

    @Column(name = "quantidade_minima_ideal")
    private int minIdealQuantity;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant updateDate;

    @Column(name = "porcentagem_desconto")
    private BigDecimal discountPercentage; // 0-100

    @Column(name = "preco_de_custo", nullable = false)
    private BigDecimal costPrice;

    @Column(name = "preco_de_venda", nullable = false)
    private BigDecimal salePrice;

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
            name = "produto_fornecedor", // name da nova tabela
            joinColumns = @JoinColumn(name = "id_produto"), // name da coluna
            inverseJoinColumns = @JoinColumn(name = "id_fornecedor") // name da coluna
    )
    private Set<Supplier> suppliers = new HashSet<>();

    public Product() {}

    public Product(Long id, String name, BigDecimal price, String description, int quantity, int minIdealQuantity, Instant updateDate, BigDecimal discountPercentage, BigDecimal costPrice, BigDecimal salePrice, Set<Category> categories, Set<Supplier> suppliers) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.minIdealQuantity = minIdealQuantity;
        this.updateDate = updateDate;
        this.discountPercentage = discountPercentage;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.categories = categories;
        this.suppliers = suppliers;
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
        if (this.price != null && this.discountPercentage != null) {
            BigDecimal fatorDesconto = this.discountPercentage
                    .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);

            BigDecimal valorDesconto = this.price.multiply(fatorDesconto);

            this.salePrice = this.price.subtract(valorDesconto)
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            this.salePrice = this.price; // Sem desconto, o preço de venda é o preço original
        }
    }

    public boolean vender(int qtd) {
        // deixei caso venha a se inserir alguma regra de negócio
        return reduzirEstoque(qtd);
    }

    public void adicionarEstoque(int quantidade) {
        if(quantidade <= 0) throw new IllegalArgumentException("A quantity deve ser positiva");

        this.quantity += quantidade;
    }

    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= 0) return false;
        if (this.quantity >= quantidade) {
            this.quantity -= quantidade;
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