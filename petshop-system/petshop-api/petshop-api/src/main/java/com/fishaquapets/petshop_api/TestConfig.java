package com.fishaquapets.petshop_api;

import com.fishaquapets.petshop_api.model.entity.Categoria;
import com.fishaquapets.petshop_api.model.entity.Produto;
import com.fishaquapets.petshop_api.repository.CategoriaRepository;
import com.fishaquapets.petshop_api.repository.FornecedorRepository;
import com.fishaquapets.petshop_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private ProdutoRepository produtoRepository;


}
