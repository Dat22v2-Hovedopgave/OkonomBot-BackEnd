package com.example.okonombotbackend.service;

import com.example.okonombotbackend.backend.service.EarningsService;
import com.example.okonombotbackend.backend.service.ExpensesService;
import com.example.okonombotbackend.backend.service.SubcategoryService;
import com.example.okonombotbackend.backend.repository.CategoryRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public SubcategoryService subcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository, UserRepository userRepository, EarningsService earningsService, ExpensesService expensesService) {
        return new SubcategoryService(subcategoryRepository, categoryRepository, userRepository, earningsService, expensesService);
    }

    @Bean
    public EarningsService earningsService() {
        return new EarningsService();
    }

    @Bean
    public ExpensesService expensesService() {
        return new ExpensesService();
    }
}
