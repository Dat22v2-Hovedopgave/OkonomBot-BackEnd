package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(int categoryId);
}
