package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    DINHEIRO(1),
    PIX(2),
    CARTAO_DEBITO(3),
    CARTAO_CREDITO(4);

    private int code;

    PaymentMethod(int code) { this.code = code; }

    private static PaymentMethod valueOf(int i) {
        for(PaymentMethod valor: PaymentMethod.values()) {
            if (valor.getCode() == i) return valor;
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
