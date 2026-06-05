package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.dto.user.SystemUserDTO;
import com.fishaquapets.petshop_api.model.entity.SystemUser;
import com.fishaquapets.petshop_api.repository.SystemUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {
    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public SystemUserDTO findById(Long id) {
        SystemUser u = systemUserRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Venda com ID " + id + " não encontrada."));
        return new SystemUserDTO(u);
    }
}
