package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.dto.financialtransaction.SaleDTO;
import com.fishaquapets.petshop_api.dto.financialtransaction.SaleResumeDTO;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.repository.SaleRepository;
import com.fishaquapets.petshop_api.repository.specifications.SaleSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    // Boa prática: Constante em UPPER_SNAKE_CASE
    private static final int LIMIT_PER_REQUEST = 25;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleDTO findById(Long id) {
        Sale s = saleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Venda com ID " + id + " não encontrada."));
        return new SaleDTO(s);
    }

    public List<SaleResumeDTO> searchDynamic(
            PaymentStatus status,
            PaymentMethod method,
            Instant startDate,
            Instant endDate,
            int limit) {

        // Empilhando as regras dinâmicas
        Specification<Sale> spec = Specification.where(SaleSpecifications.hasPaymentStatus(status))
                .and(SaleSpecifications.hasPaymentMethod(method))
                .and(SaleSpecifications.registeredBetween(startDate, endDate));

        // Protegendo o limite de paginação
        int safeLimit = Math.min(limit, LIMIT_PER_REQUEST);

        // Configurando a paginação (Atenção ao import correto do Spring Data Domain)
        Pageable pageable = PageRequest.of(0, safeLimit, Sort.by(Sort.Direction.DESC, "registrationDateTime"));

        // Executando a busca otimizada (O JpaSpecificationExecutor faz isso funcionar)
        return saleRepository.findAll(spec, pageable).getContent()
                .stream()
                .map(SaleResumeDTO::new)
                .toList();
    }
}