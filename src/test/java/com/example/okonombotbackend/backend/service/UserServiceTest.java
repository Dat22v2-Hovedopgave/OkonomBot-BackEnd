package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.UserRequest;
import com.example.okonombotbackend.backend.dto.UserResponse;
import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.repository.*;
import com.example.okonombotbackend.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(TestConfig.class)
class UserServiceTest {

    @Autowired
    SubcategoryService subcategoryService;

    @Autowired
    UserService userService;

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
    void saveUser() {
        // Test case: Saving a user with an existing email
        UserRequest existingEmailRequest = new UserRequest();
        existingEmailRequest.setUsername("UniqueUsername");
        existingEmailRequest.setPassword("password123");
        existingEmailRequest.setEmail("takemeback@mail.com");

        Exception emailException = assertThrows(Exception.class, () -> userService.saveUser(existingEmailRequest));
        assertEquals("User with this email already in use.", emailException.getMessage());

        // Test case: Saving a user with an existing username
        UserRequest existingUsernameRequest = new UserRequest();
        existingUsernameRequest.setUsername("Adolf");
        existingUsernameRequest.setPassword("password123");
        existingUsernameRequest.setEmail("unique@example.com");

        Exception usernameException = assertThrows(Exception.class, () -> userService.saveUser(existingUsernameRequest));
        assertEquals("Username already in use.", usernameException.getMessage());
    }

    @Test
    void getUserByUsername() {
        // Test case: Fetching an existing user by username
        UserResponse response = userService.getUserByUsername("Adolf");
        assertNotNull(response);
        assertEquals("Adolf", response.getUsername());
        assertEquals("takemeback@mail.com", response.getEmail());

        // Test case: Fetching a non-existing user by username
        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserByUsername("blahblah"));
        assertEquals("User not found", exception.getMessage());
    }
}