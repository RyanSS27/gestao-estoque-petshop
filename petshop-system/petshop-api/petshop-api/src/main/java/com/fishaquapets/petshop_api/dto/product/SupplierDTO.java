package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Supplier;
import lombok.Getter;

@Getter
public class SupplierDTO {
    private Long id;
    private String name;
    private String contato; // estamos na dúvida se será só telefone ou email também


    public SupplierDTO() {}

    public SupplierDTO(Long id, String name, String contato) {
        this.id = id;
        this.name = name;
        this.contato = contato;
    }

    public SupplierDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getNome();
        this.contato = supplier.getContato();
    }
}
