package br.com.fishaquapets.petshop_api.model.entity;

import java.time.LocalDateTime;

public class AlteracaoVenda {
    private String descricao;
    private LocalDateTime data;

    public  AlteracaoVenda(String descricao) {
        this.descricao = descricao;
        this.data = LocalDateTime.now();
    }
}
