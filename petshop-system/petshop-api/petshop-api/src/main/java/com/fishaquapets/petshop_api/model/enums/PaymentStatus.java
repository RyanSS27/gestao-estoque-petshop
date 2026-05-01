package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDENTE(1),
    PAGAMENTO_PARCIAL(2),
    PAGA(3),
    ESTORNADA(4);

    private int code;

    PaymentStatus(int i) {
        this.code = i;
    }

    public static PaymentStatus valueOff(int i) {
        for(PaymentStatus valor: PaymentStatus.values()) {
            if(valor.getCode() == i) return valor;
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
