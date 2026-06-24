package com.fishaquapets.petshop_api.repository.specifications;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.model.entity.ServiceProvided;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import java.time.Instant;

public class ServiceProvidedSpecifications {

    private ServiceProvidedSpecifications() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Specification<ServiceProvided> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("serviceName")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<ServiceProvided> registeredBetween(Instant startDate, Instant endDate) {
        return (root, query, cb) -> {
            // Cenário 1: Nenhuma data informada (Sempre Verdadeiro)
            if (startDate == null && endDate == null) {
                return cb.conjunction();
            }

            // Cenário 2: Ambas as datas informadas (Between)
            if (startDate != null && endDate != null) {
                return cb.between(root.get("serviceDate"), startDate, endDate);
            }

            // Cenário 3: Apenas a data de início informada (Maior ou igual)
            if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("serviceDate"), startDate);
            }

            // Cenário 4: Apenas a data de fim informada (Menor ou igual)
            return cb.lessThanOrEqualTo(root.get("serviceDate"), endDate);
        };
    }

    public static Specification<ServiceProvided> hasCategory(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return cb.conjunction();
            Join<Product, Category> categoryJoin = root.join("categories");
            return cb.equal(categoryJoin.get("id"), categoryId);
        };
    }


    public static Specification<ServiceProvided> hasStatus(PaymentStatus status) {
        return (root, query, cb) -> {
            if (status == null) return cb.conjunction();
            return cb.equal(root.get("paymentStatus"), status);
        };
    }

    public static Specification<ServiceProvided> hasPaymentMethod(PaymentMethod method) {
        return (root, query, cb) -> {
            if (method == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("paymentMethod"), method);
        };
    }
}