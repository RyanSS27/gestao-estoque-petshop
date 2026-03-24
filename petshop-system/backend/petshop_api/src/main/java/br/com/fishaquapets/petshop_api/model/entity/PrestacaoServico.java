package br.com.fishaquapets.petshop_api.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicos_prestados")
public class PrestacaoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private List<Servico> servicos = new ArrayList<>();
    private LocalDateTime data;
    private String local;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = true)
    private String Descricao;

    @Column(name = "notas_edicao", nullable = true)
    private List<Notas> notasEdicao = new ArrayList<>();

    public void adicionarNota(NotaEdicao nota) {
        this.notasEdicao.add(nota);
    }

    public void removeNota(NotaEdicao nota) {
        this.notasEdicao.remove(nota);
    }
}
