package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.SubcategoryRequest;
import com.example.okonombotbackend.backend.dto.SubcategoryResponse;
import com.example.okonombotbackend.backend.dto.earning.EarningRequest;
import com.example.okonombotbackend.backend.dto.expense.ExpenseRequest;
import com.example.okonombotbackend.backend.entity.Earning;
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

    @Autowired
    EarningsService earningsService;

    @Autowired
    ExpensesService expensesService;



    public SubcategoryResponse addSubcategory(SubcategoryRequest subcategoryRequest) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryRequest.getName());
        subcategory.setCategory(categoryRepository.findCategoryById(subcategoryRequest.getCategoryId()));
        subcategory.setUser(userRepository.findUserByUsername(subcategoryRequest.getUsername()));

        Subcategory subcategory1 = subcategoryRepository.save(subcategory);

        //If subcategory is an expense, create an expense for the subcategory
        if (subcategoryRequest.getCategoryId() == 2 || subcategoryRequest.getCategoryId() == 4 || subcategoryRequest.getCategoryId() == 5){
            // Create an expense for the subcategory
            ExpenseRequest expenseRequest = new ExpenseRequest();
            expenseRequest.setAmount(0.0);
            expenseRequest.setSubcategoryId(subcategory.getId());
            expenseRequest.setUsername(subcategoryRequest.getUsername());
            expensesService.addExpense(expenseRequest);
        } else {
        // Create an earning for the subcategory
        EarningRequest earningRequest = new EarningRequest();
        earningRequest.setAmount(0.0);
        earningRequest.setSubcategoryId(subcategory.getId());
        earningRequest.setUsername(subcategoryRequest.getUsername());
        earningsService.addEarning(earningRequest);
        }

        return new SubcategoryResponse(subcategory1);
    }
}
