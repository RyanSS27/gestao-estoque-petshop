package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MetodoPagamento {
    DINHEIRO(1),
    PIX(2),
    CARTAO_DEBITO(3),
    CARTAO_CREDITO(4);

    private int code;

    MetodoPagamento(int code) { this.code = code; }

    private static MetodoPagamento valueOf(int i) {
        for(MetodoPagamento valor: MetodoPagamento.values()) {
            if (valor.getCode() == i) return valor;
        }
    }
}
