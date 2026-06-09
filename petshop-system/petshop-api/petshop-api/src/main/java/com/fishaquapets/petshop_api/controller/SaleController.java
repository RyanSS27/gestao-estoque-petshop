package com.fishaquapets.petshop_api.controller;

import com.fishaquapets.petshop_api.dto.financialtransaction.SaleDTO;
import com.fishaquapets.petshop_api.dto.financialtransaction.SaleResumeDTO;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        SaleDTO dto = saleService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<SaleResumeDTO>> searchDynamic(
            @RequestParam(required = false) PaymentStatus status,
            @RequestParam(required = false) PaymentMethod method,
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate,
            @RequestParam(defaultValue = "25") int limit) {

        // O Controller captura e manda tudo para o service
        List<SaleResumeDTO> result = saleService.searchDynamic(status, method, startDate, endDate, limit);

        return ResponseEntity.ok().body(result);
    }
}
