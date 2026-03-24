package br.com.fishaquapets.petshop_api.model.entity;

import br.com.fishaquapets.petshop_api.model.enums.EstadoVenda;
import br.com.fishaquapets.petshop_api.model.enums.MetodoPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private List<ItemVenda> itens;
    private EstadoVenda estado;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    @Column(name = "metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @Column(nullable = true)
    private List<String> comentarios;

    @Column(name = "historico_alteracoes", nullable = true)
    private List<AlteracaoVenda> historico;

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    public BigDecimal calcularTotal() {
        return BigDecimal.valueOf(0);
    }

    public void alterarEstado(EstadoVenda estado, String descricao) {
        this.estado = estado;
        this.historico.add(new AlteracaoVenda(descricao));
    }

    public boolean pagar() {
        this.estado = EstadoVenda.PAGA;
        return true;
    }
}
