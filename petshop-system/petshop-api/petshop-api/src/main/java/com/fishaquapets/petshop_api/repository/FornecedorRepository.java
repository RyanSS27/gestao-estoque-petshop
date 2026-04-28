package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
