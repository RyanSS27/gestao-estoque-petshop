package br.com.fishaquapets.petshop_api.model.entity;

import br.com.fishaquapets.petshop_api.model.enums.CategoriaProduto;

import java.util.List;

public class Produto extends Item {
    private String descricao;
    private CategoriaProduto categoria;
    private List<Fornecedor> fornecedores;

    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public void retirarEstoque(int quantidade) {
        this.quantidade -= quantidade;
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        this.fornecedores.remove(fornecedor);
    }

    public boolean vender(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }
}
