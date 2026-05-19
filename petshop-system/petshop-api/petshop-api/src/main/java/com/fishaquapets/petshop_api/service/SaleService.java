package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.dto.sale.SaleDTO;
import com.fishaquapets.petshop_api.dto.sale.SaleResumeDTO;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    private static final int limitPerRequest = 25;

    public SaleDTO findById(Long id) {
        Sale s = saleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Venda com ID " + id + " não encontrada."));
        return new SaleDTO(s);
    }

    public List<SaleResumeDTO> findByQuantity(int quantity) {
        int safeQuantity = Math.min(quantity, limitPerRequest);

        // Criamos um pedido para a primeira página (0) com o tamanho desejado
        Pageable topN = PageRequest.of(0, safeQuantity, Sort.by(Sort.Direction.DESC, "registrationDateTime"));

        // Usamos findAll(topN).getContent() que é nativo do Spring Data
        return saleRepository.findAll(topN).getContent()
                .stream()
                .map(SaleResumeDTO::new)
                .toList();
    }
}
