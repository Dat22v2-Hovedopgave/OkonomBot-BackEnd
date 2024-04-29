package com.example.okonombotbackend.api;

import com.example.okonombotbackend.dto.SubcategoryRequest;
import com.example.okonombotbackend.entity.Subcategory;
import com.example.okonombotbackend.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @PostMapping("/create")
    public ResponseEntity createSubcategory(@RequestBody SubcategoryRequest body) {
        return ResponseEntity.ok(subcategoryService.addSubcategory(body));
    }

    @GetMapping("/{subcategoryId}")
    public Subcategory getSubcategoryById(@PathVariable int subcategoryId) {
        return null;
    }
}
