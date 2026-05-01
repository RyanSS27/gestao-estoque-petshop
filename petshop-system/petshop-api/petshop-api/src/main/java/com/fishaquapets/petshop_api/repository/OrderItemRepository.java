package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.OrderItem;
import com.fishaquapets.petshop_api.model.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
