package com.fishaquapets.petshop_api.dto.user;

import com.fishaquapets.petshop_api.model.entity.SystemUser;


public class SystemUserDTO {
    private Long id;

    private String name;

    private String company;

    private String phoneNumber;

    private String email;

    private String senha; // procurar como criptografar futuramente

    private String urlInstagram;


    public SystemUserDTO(Long id, String name, String company, String phoneNumber, String email, String urlInstagram) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.urlInstagram = urlInstagram;
    }

    public SystemUserDTO(SystemUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.company = user.getCompany();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.urlInstagram = user.getUrlInstagram();
    }
}
