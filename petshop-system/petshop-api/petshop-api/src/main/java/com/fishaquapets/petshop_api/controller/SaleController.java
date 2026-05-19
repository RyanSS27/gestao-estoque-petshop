package com.fishaquapets.petshop_api.controller;

import com.fishaquapets.petshop_api.dto.sale.SaleDTO;
import com.fishaquapets.petshop_api.dto.sale.SaleResumeDTO;
import com.fishaquapets.petshop_api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/venda")
public class SaleController {
    @Autowired
    SaleService saleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(saleService.findById(id));
    }

    @GetMapping(value = "/quantity/{quantity}")
    public ResponseEntity<List<SaleResumeDTO>> findByQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok().body(saleService.findByQuantity(quantity));
    }
}
