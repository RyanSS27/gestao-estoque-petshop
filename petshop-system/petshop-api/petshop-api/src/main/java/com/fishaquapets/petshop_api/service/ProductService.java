package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.dto.product.ProductDTO;
import com.fishaquapets.petshop_api.dto.product.ProductResumeDTO;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.repository.ProductRepository;
import com.fishaquapets.petshop_api.repository.specifications.ProductSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private static final int LIMIT_PER_REQUEST = 25;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findById(Long id) {
        Product p = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto com ID " + id + " não encontrado"));
        return new ProductDTO(p);
    }

    public List<ProductResumeDTO> search(String name, Long categoryId, Long supplierId, Integer limit) {

        // Empilhando as regras dinâmicas de busca
        // O método where() inicia a corrente e os and() vão adicionando os blocos
        Specification<Product> spec = Specification.where(ProductSpecifications.hasName(name))
                .and(ProductSpecifications.hasCategory(categoryId))
                .and(ProductSpecifications.hasSupplier(supplierId));

        // Limite de paginação
        int querySafeLimit = Math.min(limit, LIMIT_PER_REQUEST);

        // Criamos a regra de paginação que EMBUTE a ordenação decrescente por data
        Pageable pageable = PageRequest.of(0, querySafeLimit, Sort.by(Sort.Direction.DESC, "updateDate"));

        // Executando a busca com os filtros e a ordenação/limite
        return productRepository.findAll(spec, pageable)
                .stream()
                .map(ProductResumeDTO::new)
                .toList();
    }
}