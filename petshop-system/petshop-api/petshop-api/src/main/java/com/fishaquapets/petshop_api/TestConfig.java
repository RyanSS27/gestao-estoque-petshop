package com.fishaquapets.petshop_api;

import com.fishaquapets.petshop_api.model.entity.Categoria;
import com.fishaquapets.petshop_api.model.entity.Fornecedor;
import com.fishaquapets.petshop_api.model.entity.Produto;
import com.fishaquapets.petshop_api.repository.CategoriaRepository;
import com.fishaquapets.petshop_api.repository.FornecedorRepository;
import com.fishaquapets.petshop_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Categorias
        Categoria c1 = new Categoria(null, "Alimentos");
        Categoria c2 = new Categoria(null, "Acessórios");
        Categoria c3 = new Categoria(null, "Higiene");
        Categoria c4 = new Categoria(null, "Peixes");
        Categoria c5 = new Categoria(null, "Aquarismo");
        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        // Fornecedores
        Fornecedor f1 = new Fornecedor(null, "PetDistribuidora S.A.", "contato@petdist.com");
        Fornecedor f2 = new Fornecedor(null, "MegaPet Atacado", "vendas@megapet.com");
        Fornecedor f3 = new Fornecedor(null, "Aquários Brasil Ltda.", "contato@aquabrasil.com");
        Fornecedor f4 = new Fornecedor(null, "OceanTech", "suporte@oceantech.com");
        fornecedorRepository.saveAll(Arrays.asList(f1, f2, f3, f4));

        // Produtos
        Produto p1 = new Produto(null, "Ração Premium 15kg", new BigDecimal("250.00"),
                "Ração de alta qualidade para cães adultos", new BigDecimal("10.00"));

        Produto p2 = new Produto(null, "Shampoo Pet suave", new BigDecimal("45.00"),
                "Shampoo hipoalergênico", BigDecimal.ZERO);

        Produto p3 = new Produto(null, "Coleira de Couro", new BigDecimal("80.00"),
                "Coleira resistente tamanho G", new BigDecimal("5.00"));
        Produto p4 = new Produto(null, "Aquário 20L Básico", new BigDecimal("150.00"),
                "Aquário de vidro simples com tampa", new BigDecimal("5.00"));

        Produto p5 = new Produto(null, "Aquário 100L Profissional", new BigDecimal("850.00"),
                "Vidro extra clear com acabamento em silicone preto", new BigDecimal("10.00"));

        Produto p6 = new Produto(null, "Filtro Externo Hang-on 300L/H", new BigDecimal("120.00"),
                "Filtragem química, física e biológica", BigDecimal.ZERO);

        Produto p7 = new Produto(null, "Termostato 50W", new BigDecimal("85.00"),
                "Aquecedor automático com regulagem", new BigDecimal("15.00"));

        Produto p8 = new Produto(null, "Luminária LED RGB 40cm", new BigDecimal("210.00"),
                "Iluminação potente para plantas naturais", new BigDecimal("8.00"));

        // Associando
        p1.addCategoria(c1);
        p1.addFornecedor(f1);

        p2.addCategoria(c3);
        p2.addFornecedor(f1);
        p2.addFornecedor(f2); // Um produto com dois fornecedores

        p3.addCategoria(c2);
        p3.addFornecedor(f2);
        p4.addCategoria(c5);
        p4.addFornecedor(f3);

        p5.addCategoria(c5);
        p5.addFornecedor(f3);

        p6.addCategoria(c5);
        p6.addFornecedor(f4);

        p7.addCategoria(c5);
        p7.addFornecedor(f4);

        p8.addCategoria(c5);
        p8.addCategoria(c2); // Aquarismo e Acessórios
        p8.addFornecedor(f4);

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

        System.out.println("Seeding realizado com sucesso!");
    }

}
