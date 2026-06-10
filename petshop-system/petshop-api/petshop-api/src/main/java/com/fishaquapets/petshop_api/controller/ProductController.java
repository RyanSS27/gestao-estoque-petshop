package com.fishaquapets.petshop_api.controller;

import com.fishaquapets.petshop_api.dto.product.ProductDTO;
import com.fishaquapets.petshop_api.dto.product.ProductResumeDTO;
import com.fishaquapets.petshop_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResumeDTO>> searchProducts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "supplierId", required = false) Long supplierId,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        List<ProductResumeDTO> list = productService.search(name, categoryId, supplierId, limit);
        return ResponseEntity.ok().body(list);
    }
}
