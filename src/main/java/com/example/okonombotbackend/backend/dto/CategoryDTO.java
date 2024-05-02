package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private int id;
    private String name;
    private Category.Type type;

    public static CategoryDTO getCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setType(category.getType());
        return categoryDTO;
    }
}
