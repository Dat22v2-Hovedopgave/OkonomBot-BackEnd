package com.example.okonombotbackend.repository;

import com.example.okonombotbackend.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    Subcategory findSubcategoryBySubcategoryId(int subcategoryId);
}
