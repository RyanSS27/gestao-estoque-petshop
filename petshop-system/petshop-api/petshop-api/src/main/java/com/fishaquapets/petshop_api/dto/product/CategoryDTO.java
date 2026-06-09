package com.fishaquapets.petshop_api.dto.product;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.enums.CategoryType;
import lombok.Getter;

@Getter
public class CategoryDTO {
    private Long id;
    private String name;
    private CategoryType categoryType;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String name, CategoryType categoryType) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.categoryType = category.getCategoryType();
    }
}
