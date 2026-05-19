package com.fishaquapets.petshop_api.service;

import com.fishaquapets.petshop_api.model.entity.SystemUser;
import com.fishaquapets.petshop_api.repository.SystemUserRepository;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {
    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }


    public SystemUserDTO findById(Long id) {
        return systemUserRepository.findById(id);
    }
}
