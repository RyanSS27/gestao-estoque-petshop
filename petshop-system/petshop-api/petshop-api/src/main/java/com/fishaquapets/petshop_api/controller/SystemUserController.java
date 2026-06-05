package com.fishaquapets.petshop_api.controller;

import com.fishaquapets.petshop_api.dto.user.SystemUserDTO;
import com.fishaquapets.petshop_api.service.SystemUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class SystemUserController {
    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<SystemUserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(systemUserService.findById(id));
    }
}
