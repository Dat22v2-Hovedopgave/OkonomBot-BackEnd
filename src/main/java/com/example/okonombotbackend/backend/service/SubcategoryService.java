package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

}
