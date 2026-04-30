package com.fishaquapets.petshop_api.model.entity;

import com.fishaquapets.petshop_api.model.enums.EstadoPagamento;
import com.fishaquapets.petshop_api.model.enums.MetodoPagamento;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendas")
public class Venda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_venda")
    private Long id;

    @Column(name = "estado_pagamento", nullable = false)
    private EstadoPagamento estadoPagamento;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @Column
    private BigDecimal pagamento;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.venda")
    private Set<ItemPedido> itens = new HashSet<>();

    @Setter(AccessLevel.NONE)
    private List<String> comentarios = new ArrayList<>();


    public Venda() {}

    public Venda(
            Long id, EstadoPagamento estadoPagamento,
            MetodoPagamento metodoPagamento, BigDecimal pagamento, List<String> comentarios
    ) {
        this.id = id;
        this.estadoPagamento = estadoPagamento;
        this.metodoPagamento = metodoPagamento;
        this.pagamento = pagamento;
        this.comentarios = comentarios;
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedido::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PrePersist
    @PreUpdate
    private void atualizarTotal() {
        calcularValorTotal();
    }

    // Falta criar os métodos de adição/subtração de itens
    public void addItem(Produto produto, Integer quantidade) {
        this.itens.add(new ItemPedido(this, produto, quantidade));
    }

    public void removeItem(ItemPedido itemPedido) {
        this.itens.remove(itemPedido);
    }

    public void addComentario(String comentario) {
        this.comentarios.add(comentario);
    }
}
