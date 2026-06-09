package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDENTE,
    PAGAMENTO_PARCIAL,
    PAGA,
    ESTORNADA;
}
