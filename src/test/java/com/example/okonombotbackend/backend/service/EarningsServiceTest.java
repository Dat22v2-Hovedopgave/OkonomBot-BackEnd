package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.earning.EarningDetailedResponse;
import com.example.okonombotbackend.backend.dto.earning.EarningRequest;
import com.example.okonombotbackend.backend.dto.earning.EarningResponse;
import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.repository.*;
import com.example.okonombotbackend.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
class EarningsServiceTest {

    @Autowired
    EarningsService earningsService;

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

        User user1 = new User();
        user1.setUsername("Adolf");
        user1.setPassword("yippy");
        user1.setEmail("takemeback@mail.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("Ferhat");
        user2.setPassword("yippy");
        user2.setEmail("pappaspizza@gmail.com");
        userRepository.save(user2);

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
        bonus.setUser(user2);
        bonus.setName("Bonus");
        bonus.setCategory(category1);
        subcategoryRepository.save(bonus);

        // Create subcategory 2 linked to Rent
        Subcategory apartmentRent = new Subcategory();
        apartmentRent.setUser(user1);
        apartmentRent.setName("Apartment Rent");
        apartmentRent.setCategory(category2);
        subcategoryRepository.save(apartmentRent);
    }

    @Test
    void addEarning() {
        EarningRequest request = new EarningRequest();
        request.setUsername("Adolf");
        request.setAmount(1000.0);
        request.setSubcategoryId(subcategoryRepository.findSubcategoryByName("Bonus").getId());

        EarningResponse response = earningsService.addEarning(request);

        assertNotNull(response);
        assertEquals("Adolf", response.getUsername());
        assertEquals(1000.0, response.getAmount());
    }

    @Test
    void getEarningsByUsername() {
        EarningRequest request = new EarningRequest();
        request.setUsername("Adolf");
        request.setAmount(1000.0);
        request.setSubcategoryId(subcategoryRepository.findSubcategoryByName("Bonus").getId());

        earningsService.addEarning(request);

        List<EarningDetailedResponse> earnings = earningsService.getEarningsByUsername("Adolf");

        assertNotNull(earnings);
        assertFalse(earnings.isEmpty());
        assertEquals("Adolf", earnings.get(0).getUsername());
    }

    @Test
    void addEarnings() {
        EarningRequest request1 = new EarningRequest();
        request1.setUsername("Adolf");
        request1.setAmount(1000.0);
        request1.setSubcategoryId(subcategoryRepository.findSubcategoryByName("Bonus").getId());

        EarningRequest request2 = new EarningRequest();
        request2.setUsername("Ferhat");
        request2.setAmount(2000.0);
        request2.setSubcategoryId(subcategoryRepository.findSubcategoryByName("Apartment Rent").getId());

        List<Earning> earnings = earningsService.addEarnings(List.of(request1, request2));

        assertNotNull(earnings);
        assertEquals(2, earnings.size());
    }

    @Test
    void deleteEarning() {
        EarningRequest request = new EarningRequest();
        request.setUsername("Adolf");
        request.setAmount(1000.0);
        request.setSubcategoryId(subcategoryRepository.findSubcategoryByName("Bonus").getId());

        EarningResponse response = earningsService.addEarning(request);

        earningsService.deleteEarning(response.getEarningId());

        assertFalse(earningsRepository.existsById(response.getEarningId()));
    }
}