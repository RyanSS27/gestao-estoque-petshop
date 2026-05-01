package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {
    PENDENTE(1),
    PAGAMENTO_PARCIAL(2),
    PAGA(3),
    ESTORNADA(4);

    private int code;

    EstadoPagamento(int i) {
        this.code = i;
    }

    public static EstadoPagamento valueOff(int i) {
        for(EstadoPagamento valor: EstadoPagamento.values()) {
            if(valor.getCode() == i) return valor;
        }
    }
}
