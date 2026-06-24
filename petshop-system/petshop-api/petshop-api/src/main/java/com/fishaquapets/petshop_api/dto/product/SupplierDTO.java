package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Supplier;
import lombok.Getter;

@Getter
public class SupplierDTO {
    private Long id;
    private String name;
    private String contact; // estamos na dúvida se será só telefone ou email também


    public SupplierDTO() {}

    public SupplierDTO(Long id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public SupplierDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.contact = supplier.getContact();
    }
}
