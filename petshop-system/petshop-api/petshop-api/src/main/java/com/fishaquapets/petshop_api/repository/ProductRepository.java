package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    // Apenas métodos adicionais que não dependam de Specification ficam aqui (ex: o seu de updateDate)
    Page<Product> findAllByOrderByUpdateDateDesc(Pageable pageable);
}