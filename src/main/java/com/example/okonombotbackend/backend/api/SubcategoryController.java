package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @PostMapping("/")
    public Subcategory createSubcategory(@RequestBody Subcategory subcategory) {
        return null;
    }

    @GetMapping("/{subcategoryId}")
    public Subcategory getSubcategoryById(@PathVariable int subcategoryId) {
        return null;
    }
}
