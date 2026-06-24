package com.fishaquapets.petshop_api.controller;

import com.fishaquapets.petshop_api.dto.financialtransaction.ServiceProvidedDTO;
import com.fishaquapets.petshop_api.dto.financialtransaction.ServiceProvidedResumeDTO;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.service.ServiceProvidedService;
import org.hibernate.query.spi.Limit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ServiceProvidedController {
    private final ServiceProvidedService serviceProvidedService;

    public ServiceProvidedController(ServiceProvidedService serviceProvidedService) {
        this.serviceProvidedService = serviceProvidedService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceProvidedDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceProvidedService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceProvidedResumeDTO>> searchDynamic(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "start-date", required = false) Instant startDate,
            @RequestParam(value = "end-date", required = false) Instant endDate,
            @RequestParam(value = "category-id", required = false) Long categoryId,
            @RequestParam(value = "payment-status", required = false) PaymentStatus status,
            @RequestParam(value = "payment-method", required = false) PaymentMethod method,
            @RequestParam(value = "limit", required = false, defaultValue = "25") int limit
            ){
        return ResponseEntity.ok().body(serviceProvidedService.searchDynamic(
                name, startDate, endDate, categoryId, status, method, limit)
        );
    }
}
