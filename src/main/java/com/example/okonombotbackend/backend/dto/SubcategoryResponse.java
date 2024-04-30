package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubcategoryResponse {
    private int subcategoryId;
    private int categoryId;
    private String name;

    public SubcategoryResponse(Subcategory subcategory) {
        this.subcategoryId = subcategory.getSubcategoryId();
        this.categoryId = subcategory.getCategory().getCategoryId();
        this.name = subcategory.getName();
    }
}