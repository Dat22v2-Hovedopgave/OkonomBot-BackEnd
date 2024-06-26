package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.CategoryResponse;
import com.example.okonombotbackend.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.okonombotbackend.backend.entity.Category;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses;
        categoryResponses = categories.stream().map(category -> new CategoryResponse(category)).toList();
        return categoryResponses;
    }
}
