package com.fishaquapets.petshop_api.repository.specifications;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.model.entity.Supplier;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return cb.conjunction(); // conjunction() funciona como um "AND 1=1" (ignora o filtro)
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return cb.conjunction();
            Join<Product, Category> categoryJoin = root.join("categories");
            return cb.equal(categoryJoin.get("id"), categoryId);
        };
    }

    public static Specification<Product> hasSupplier(Long supplierId) {
        return (root, query, cb) -> {
            if (supplierId == null) return cb.conjunction();
            Join<Product, Supplier> supplierJoin = root.join("suppliers");
            return cb.equal(supplierJoin.get("id"), supplierId);
        };
    }
}