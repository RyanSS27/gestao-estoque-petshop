package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.dto.product.ProductDTO;
import com.fishaquapets.petshop_api.dto.product.ProductResumeDTO;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    private static final int limitPerRequest = 25;

    
    public List<ProductResumeDTO> findByQuantity(int quantity) {
        // Coloquei um freio na quantidade de requisições por vez
        if (quantity > limitPerRequest) quantity = limitPerRequest;


        // Criamos um pedido para a primeira página (0) com o tamanho desejado
        Pageable topN = PageRequest.of(0, quantity);

        return productRepository.findAllByOrderByUpdateDateDesc(topN)
                .stream()
                .map(ProductResumeDTO::new) // Assume que o DTO tem um construtor que recebe a Entity e converte
                .toList();
    }

    public ProductDTO findById(Long id) {
        Product p = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado"));;
        return new ProductDTO(p);
    }

    // Implementar a lógica das requisições:
    public List<ProductResumeDTO> findByCategory(String category) {
        // Falta a lógica
        return null;
    }

    public List<ProductResumeDTO> findByCategory(Long category) {
        // Falta a lógica
        return null;
    }

    public List<ProductResumeDTO> findBySupplier(String supplier) {
        return null;
    }

    public List<ProductResumeDTO> findBySupplier(Long supplier) {
        return null;
    }

    public List<ProductResumeDTO> findByKeyWords(String keyWords) {
        // Falta a lógica
        return null;
    }
}
