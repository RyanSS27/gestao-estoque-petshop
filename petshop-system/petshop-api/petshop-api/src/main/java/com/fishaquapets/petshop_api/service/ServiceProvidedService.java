package com.fishaquapets.petshop_api.service;
import com.fishaquapets.petshop_api.dto.financialtransaction.ServiceProvidedDTO;
import com.fishaquapets.petshop_api.dto.financialtransaction.ServiceProvidedResumeDTO;
import com.fishaquapets.petshop_api.model.entity.ServiceProvided;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.repository.ServiceProvidedRepository;
import com.fishaquapets.petshop_api.repository.specifications.ServiceProvidedSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ServiceProvidedService {
    private final ServiceProvidedRepository serviceProvidedRepository;
    private static final int LIMIT_PER_REQUEST = 25;

    public ServiceProvidedService(ServiceProvidedRepository serviceProvidedRepository) {
        this.serviceProvidedRepository = serviceProvidedRepository;
    }

    public ServiceProvidedDTO findById(Long id) {
        ServiceProvided s = serviceProvidedRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Seriço com ID " + id + " não encontrada."));

        return new ServiceProvidedDTO(s);
    }

    public List<ServiceProvidedResumeDTO> searchDynamic(
            String name, Instant startDate, Instant endDate,
            Long categoryId, PaymentStatus status, PaymentMethod method,
            int limit
    ) {
        Specification<ServiceProvided> specifications = Specification.where(ServiceProvidedSpecifications.hasName(name))
                .and(ServiceProvidedSpecifications.registeredBetween(startDate, endDate))
                .and(ServiceProvidedSpecifications.hasCategory(categoryId))
                .and(ServiceProvidedSpecifications.hasPaymentMethod(method))
                .and(ServiceProvidedSpecifications.hasStatus(status));

        int querySafeLimit = Math.min(LIMIT_PER_REQUEST, limit);

        Pageable pageable = PageRequest.of(0, querySafeLimit, Sort.by(Sort.Direction.DESC, "registrationDateTime"));

        return serviceProvidedRepository.findAll(specifications, pageable).getContent()
                .stream()
                .map(ServiceProvidedResumeDTO::new)
                .toList();
    }
}
