package com.example.okonombotbackend.service;

import com.example.okonombotbackend.entity.Subcategory;
import com.example.okonombotbackend.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

}
