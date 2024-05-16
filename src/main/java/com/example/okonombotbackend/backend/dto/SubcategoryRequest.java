package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubcategoryRequest {
    private int categoryId;
    private String name;
    private String username;

    public SubcategoryRequest(Subcategory subcategory) {
        this.categoryId = subcategory.getCategory().getId();
        this.name = subcategory.getName();
        this.username = subcategory.getUsers().getUsername();
    }
}
