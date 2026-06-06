package com.fishaquapets.petshop_api.repository.specifications;

import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import org.springframework.data.jpa.domain.Specification;
import java.time.Instant;

public class SaleSpecifications {

    public static Specification<Sale> hasPaymentStatus(PaymentStatus status) {
        return (root, query, cb) -> {
            if (status == null) return cb.conjunction();
            return cb.equal(root.get("paymentStatus"), status);
        };
    }

    public static Specification<Sale> hasPaymentMethod(PaymentMethod method) {
        return (root, query, cb) -> {
            if (method == null) return cb.conjunction();
            return cb.equal(root.get("paymentMethod"), method);
        };
    }

    public static Specification<Sale> registeredBetween(Instant startDate, Instant endDate) {
        return (root, query, cb) -> {
            if (startDate != null && endDate != null) {
                return cb.between(root.get("registrationDateTime"), startDate, endDate);
            }
            if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("registrationDateTime"), startDate);
            }
            if (endDate != null) {
                return cb.lessThanOrEqualTo(root.get("registrationDateTime"), endDate);
            }
            return cb.conjunction();
        };
    }
}
