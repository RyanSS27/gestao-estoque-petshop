package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.ServiceProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Long>, JpaSpecificationExecutor<ServiceProvided> {
}
