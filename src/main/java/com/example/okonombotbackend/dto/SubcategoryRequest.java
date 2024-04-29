package com.example.okonombotbackend.dto;

import com.example.okonombotbackend.entity.Subcategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubcategoryRequest {
    private int categoryId;
    private String name;

    public static SubcategoryRequest getSubcategoryRequest(Subcategory subcategory) {
        SubcategoryRequest subcategoryRequest = new SubcategoryRequest();
        subcategoryRequest.setCategoryId(subcategory.getCategory().getCategoryId()); // Access the category ID directly
        subcategoryRequest.setName(subcategory.getName());
        return subcategoryRequest;
    }
}
