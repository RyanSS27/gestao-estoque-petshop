package com.fishaquapets.petshop_api.model.enums;

public enum EstadoPagamento {
    PENDENTE(1),
    PAGAMENTO_PARCIAL(2),
    PAGA(3),
    ESTORNADA(4);

    EstadoPagamento(int i) {
    }
}
