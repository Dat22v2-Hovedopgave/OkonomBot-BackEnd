package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.CategoryResponse;
import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}
