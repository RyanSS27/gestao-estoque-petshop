package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Busca os produtos ordenados pela data de atualização decrescente
    public List<Product> findAllByOrderByUpdateDateDesc(Pageable pageable);
}
