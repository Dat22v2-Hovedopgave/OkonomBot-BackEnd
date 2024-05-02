package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Subcategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubcategoryDTO {
    private int id;
    private int categoryId;
    private String name;

    public static SubcategoryDTO getSubcategoryDTO(Subcategory subcategory) {
        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        subcategoryDTO.setId(subcategory.getId());
        subcategoryDTO.setCategoryId(subcategory.getCategory().getId()); // Access the category ID directly
        subcategoryDTO.setName(subcategory.getName());
        return subcategoryDTO;
    }
}
