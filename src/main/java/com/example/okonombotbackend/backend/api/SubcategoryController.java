package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.SubcategoryRequest;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @PostMapping("/addSubcategory")
    public ResponseEntity<?> addSubcategory(@RequestBody SubcategoryRequest body) {
        return ResponseEntity.ok(subcategoryService.addSubcategory(body));
    }

    @GetMapping("/{subcategoryId}")
    public Subcategory getSubcategoryById(@PathVariable int subcategoryId) {
        return null;
    }
}
