package com.example.okonombotbackend.service;

import com.example.okonombotbackend.backend.dto.SubcategoryRequest;
import com.example.okonombotbackend.backend.dto.SubcategoryResponse;
import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.repository.*;
import com.example.okonombotbackend.backend.service.SubcategoryService;
import com.example.okonombotbackend.security.entity.Users;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import(TestConfig.class)
@Transactional
@Rollback
class SubcategoryServiceTest {

    @Autowired
    SubcategoryService subcategoryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EarningsRepository earningsRepository;

    @Autowired
    ExpensesRepository expensesRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @BeforeEach
    public void initData(){

    Users users1 = new Users();
        users1.setUsername("Adolf");
        users1.setPassword("yippy");
        users1.setEmail("takemeback@mail.com");
        userRepository.save(users1);

    Users users2 = new Users();
        users2.setUsername("Ferhat");
        users2.setPassword("yippy");
        users2.setEmail("pappaspizza@gmail.com");
        userRepository.save(users2);

    Category category1 = new Category();
        category1.setName("Salary");
        category1.setType(Category.Type.earning);
        categoryRepository.save(category1);

        // Create category 2
    Category category2 = new Category();
        category2.setName("Rent");
        category2.setType(Category.Type.expense);
        categoryRepository.save(category2);

        // Create subcategory 1 linked to Salary
    Subcategory bonus = new Subcategory();
        bonus.setUsers(users2);
        bonus.setName("Bonus");
        bonus.setCategory(category1);
        subcategoryRepository.save(bonus);

        // Create subcategory 2 linked to Rent
    Subcategory apartmentRent = new Subcategory();
        apartmentRent.setUsers(users1);
        apartmentRent.setName("Apartment Rent");
        apartmentRent.setCategory(category2);
        subcategoryRepository.save(apartmentRent);
    }

    @Test
    void addSubcategory() {

        Subcategory apartmentRent = new Subcategory();
        apartmentRent.setUsers(userRepository.findUsersByUsername("Ferhat"));
        apartmentRent.setName("Apartment Rent");
        apartmentRent.setCategory(categoryRepository.findCategoryById(2));

        SubcategoryRequest newRequest = new SubcategoryRequest(apartmentRent);

        SubcategoryResponse newResponse = subcategoryService.addSubcategory(newRequest);

        assertEquals(newRequest.getCategoryId(),newResponse.getCategoryId(),"Do category ID stay the same?");
        assertEquals(newRequest.getName(),newResponse.getName(),"Do the subcategory names stay the same");
        assertEquals(newRequest.getUsername(),newResponse.getUsername(),"Do usernames (User ID) stay the same?");

        assertEquals(1,1,"Is one equals one?");
    }
}