package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.ItemPedido;
import com.fishaquapets.petshop_api.model.pk.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
