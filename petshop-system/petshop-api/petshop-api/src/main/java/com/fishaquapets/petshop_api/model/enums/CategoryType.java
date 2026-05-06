package com.fishaquapets.petshop_api.model.enums;

import lombok.Getter;

@Getter
public enum CategoryType {
    PRODUCT("produto"),
    SERVICE("servico");

    private String category;

    CategoryType(String category) {this.category = category;}

    public static CategoryType fromValue(String value) {
        for (CategoryType type : CategoryType.values()) {
            if (type.getCategory().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Categoria inválida: " + value);
    }
}
