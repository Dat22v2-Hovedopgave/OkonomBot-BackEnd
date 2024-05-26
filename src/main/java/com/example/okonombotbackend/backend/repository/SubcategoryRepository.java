package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    Subcategory findSubcategoryById(int subcategoryId);

    Subcategory findSubcategoryByName(String bonus);

    Subcategory findByNameAndCategory(String subcategoryName, Category category);
}
