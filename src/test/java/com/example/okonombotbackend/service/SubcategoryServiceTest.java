package com.example.okonombotbackend.service;

import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.repository.CategoryRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.dto.SubcategoryRequest;
import com.example.okonombotbackend.backend.dto.SubcategoryResponse;
import com.example.okonombotbackend.backend.service.SubcategoryService;
import com.example.okonombotbackend.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubcategoryServiceTest {

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;


    SubcategoryService subcategoryService;

    @BeforeEach
    void initData() {

        //Users
        User user1 = new User();
        user1.setUsername("Adolf");
        user1.setPassword("yippy");
        user1.setEmail("takemeback@mail.com");
        userRepository.save(user1);

        //Category
        Category category2 = new Category();
        category2.setName("Rent");
        category2.setType(Category.Type.expense);
        categoryRepository.save(category2);

        //Subcategory
        Subcategory apartmentRent = new Subcategory();
        apartmentRent.setUser(user1);
        apartmentRent.setName("Apartment Rent");
        apartmentRent.setCategory(category2);
        subcategoryRepository.save(apartmentRent);

        subcategoryService = new SubcategoryService(subcategoryRepository,categoryRepository,userRepository);
    }

    @Test
    void addSubcategory() {

        User user2 = new User();
        user2.setUsername("Ferhat");
        user2.setPassword("yippy");
        user2.setEmail("pappaspizza@gmail.com");
        userRepository.save(user2);

        Category category1 = new Category();
        category1.setName("Salary");
        category1.setType(Category.Type.earning);
        categoryRepository.save(category1);

        Subcategory bonus = new Subcategory();
        bonus.setUser(user2);
        bonus.setName("Bonus");
        bonus.setCategory(category1);
        subcategoryRepository.save(bonus);

        SubcategoryRequest tmpSubcategory = new SubcategoryRequest(bonus);

        SubcategoryResponse tmpSubcategoryResponse = subcategoryService.addSubcategory(tmpSubcategory);

        assertEquals("Ferhat",tmpSubcategoryResponse.getUsername(),"Same username?");
        assertEquals("Bonus",tmpSubcategoryResponse.getName(),"Same Subcategory name?");
        assertEquals(2,tmpSubcategoryResponse.getSubcategoryId(),"Same id?");
    }
}
