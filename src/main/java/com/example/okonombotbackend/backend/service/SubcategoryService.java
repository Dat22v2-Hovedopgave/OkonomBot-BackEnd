package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.SubcategoryRequest;
import com.example.okonombotbackend.backend.dto.SubcategoryResponse;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.repository.CategoryRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    public SubcategoryResponse addSubcategory(SubcategoryRequest subcategoryRequest) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryRequest.getName());
        subcategory.setCategory(categoryRepository.findCategoryById(subcategoryRequest.getCategoryId()));
        subcategory.setUser(userRepository.findUserByUsername(subcategoryRequest.getUsername()));

        return new SubcategoryResponse(subcategoryRepository.save(subcategory));
    }
}
