package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
