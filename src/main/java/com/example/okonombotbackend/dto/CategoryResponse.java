package com.example.okonombotbackend.dto;

import com.example.okonombotbackend.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    private int categoryId;
    private String name;
    private Category.Type type;

    public CategoryResponse(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
        this.type = category.getType();
    }
}
