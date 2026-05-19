package com.fishaquapets.petshop_api.repository;

import com.fishaquapets.petshop_api.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    /*
        Quando a query for realizada para buscar a Sale, ele puxa todas os itens
        relativos a ela. Isso pode gerar busca por dados em excesso dependendo do
        caso, mas aqui teríamos que pegar para carregar os itens no SaleDTO que
        traz todos os itens da venda para mostrar ao cliente.

        Se não fosse assim, teriamos que consultar item por item no banco de dados
    */
    @Query("SELECT s FROM Sale s LEFT JOIN FETCH s.itens WHERE s.id = :id")
    Optional<Sale> findByIdWithItems(@Param("id") Long id);
}
