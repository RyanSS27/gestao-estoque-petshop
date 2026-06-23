package com.fishaquapets.petshop_api.dto.financialtransaction;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.entity.ServiceProvided;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ServiceProvidedResumeDTO {
    private Long id;
    private BigDecimal totalValue;
    private String serviceName;
    private Set<Category> categories = new HashSet<>();
    private Instant serviceDate;

    public ServiceProvidedResumeDTO(ServiceProvided service) {
        this.id = service.getId();
        this.totalValue = service.getTotalValue();
        this.serviceName = service.getServiceName();
        this.categories = service.getCategories();
        this.serviceDate = service.getServiceDate();
    }

    public ServiceProvidedResumeDTO(Long id, BigDecimal totalValue, String serviceName, Set<Category> categories, Instant serviceDate) {
        this.id = id;
        this.totalValue = totalValue;
        this.serviceName = serviceName;
        this.categories = categories;
        this.serviceDate = serviceDate;
    }
}
