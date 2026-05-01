package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    // Classe de testes, não diagramar
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> objeto = productRepository.findById(id);
        return objeto.get();
    }
}
