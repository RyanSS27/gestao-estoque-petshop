package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
