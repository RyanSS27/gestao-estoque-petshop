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
@RequestMapping(value = "/product")
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
    // Buscar os adicionados/alterados recentemente por quantidade
    @GetMapping(value = "/quantity/{quantity}")
    public ResponseEntity<List<ProductResumeDTO>> findByQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok().body(productService.findByQuantity(quantity));
    }

    // BUSCA POR CATEGORIA
    // Buscar por categoria
    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<ProductResumeDTO>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(productService.findByCategory(category));
    }
    // sobrecarga do busca por Id
    public ResponseEntity<List<ProductResumeDTO>> findByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(productService.findByCategory(categoryId));
    }



    // >> FUNÇÕES AINDA SEM LÓGICA POR TRÁS <<


    // BUSCA POR FORNECEDOR
    // Buscar por fornecedor
    @GetMapping(value = "/supplier/{supplier}")
    public ResponseEntity<List<ProductResumeDTO>> findBySupplier(@PathVariable String supplier) {
        return ResponseEntity.ok().body(productService.findBySupplier(supplier));
    }
    // sobrecarga de busca por Id
    public ResponseEntity<List<ProductResumeDTO>> findBySupplier(@PathVariable Long supplier) {
        return ResponseEntity.ok().body(productService.findBySupplier(supplier));
    }

    // BUSCA POR NOME / PALAVRAS-CHAVE
    // Retorna os produtos com trechos de nomes compatíveis
    public ResponseEntity<List<ProductResumeDTO>> findByKeyWords(@PathVariable String keyWords) {
        return ResponseEntity.ok().body(productService.findByKeyWords(keyWords));
    }

    // MAPEAMENTO DAS ROTAS DE SET
}
