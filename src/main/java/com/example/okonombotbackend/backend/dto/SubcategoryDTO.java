package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Subcategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubcategoryDTO {
    private int subcategoryId;
    private int categoryId;
    private String name;

    public static SubcategoryDTO getSubcategoryDTO(Subcategory subcategory) {
        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        subcategoryDTO.setSubcategoryId(subcategory.getSubcategoryId());
        subcategoryDTO.setCategoryId(subcategory.getCategory().getCategoryId()); // Access the category ID directly
        subcategoryDTO.setName(subcategory.getName());
        return subcategoryDTO;
    }
}
