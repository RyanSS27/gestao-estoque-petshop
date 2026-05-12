package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
}
