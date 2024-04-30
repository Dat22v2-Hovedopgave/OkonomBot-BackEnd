package com.example.okonombotbackend.service;

import com.example.okonombotbackend.dto.SubcategoryRequest;
import com.example.okonombotbackend.dto.SubcategoryResponse;
import com.example.okonombotbackend.entity.Subcategory;
import com.example.okonombotbackend.repository.CategoryRepository;
import com.example.okonombotbackend.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public SubcategoryResponse addSubcategory(SubcategoryRequest subcategoryRequest) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryRequest.getName());
        subcategory.setCategory(categoryRepository.findCategoryByCategoryId(subcategoryRequest.getCategoryId()));

        return new SubcategoryResponse(subcategoryRepository.save(subcategory));
    }
}
