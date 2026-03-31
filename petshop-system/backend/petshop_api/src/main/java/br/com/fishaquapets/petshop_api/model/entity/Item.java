package br.com.fishaquapets.petshop_api.model.entity;

import java.math.BigDecimal;

public abstract class Item {
    protected Long id;
    protected String nome;
    protected BigDecimal preco;
    protected int quantidade;
}
