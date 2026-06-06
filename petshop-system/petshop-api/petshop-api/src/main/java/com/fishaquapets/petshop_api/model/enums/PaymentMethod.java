package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    DINHEIRO,
    PIX,
    CARTAO_DEBITO,
    CARTAO_CREDITO;
}
