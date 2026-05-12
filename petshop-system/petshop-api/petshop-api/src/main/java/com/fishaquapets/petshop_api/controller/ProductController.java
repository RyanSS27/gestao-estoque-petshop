package com.fishaquapets.petshop_api.controller;

import com.fishaquapets.petshop_api.dto.product.ProductDTO;
import com.fishaquapets.petshop_api.dto.product.ProductResumeDTO;
import com.fishaquapets.petshop_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProductController {
    @Autowired
    ProductService productService;

    // MAPEAMENTO DAS ROTAS DE GET
    // Classe de testes, não diagramar
    // @GetMapping(value = "/todos")
    // public ResponseEntity<List<ProductDTO>> findAll() { return ResponseEntity.ok().body(productService.findAll()); }

    // BUSCA POR ID
    // Retorna todas as informações do produto
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    // BUSCA POR QUANTIDADE
    // Buscar os mais recentes por quantidade
    @GetMapping(value = "/quantity/{quantity}")
    public ResponseEntity<List<ProductResumeDTO>> findByQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok().body(productService.findByQuantity(quantity));
    }
    /*
    Faltam:
        - Buscar os mais recentes por quantidade

        - Busca por categoria

        - Busca por fornecedor

        - Busca por nome/palavras-chave (retorna uma lista com produtos que tenham partes compatíveis com as string)
    */

    // MAPEAMENTO DAS ROTAS DE SET
}
