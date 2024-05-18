package com.example.okonombotbackend.backend.config;

import com.example.okonombotbackend.backend.entity.Category;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.backend.repository.*;
import com.example.okonombotbackend.security.entity.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class DeveloperData implements ApplicationRunner {

    //Repositories
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    SubcategoryRepository subcategoryRepository;
    ExpensesRepository expensesRepository;
    EarningsRepository earningsRepository;

    public DeveloperData(UserRepository userRepository, CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository, ExpensesRepository expensesRepository, EarningsRepository earningsRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.expensesRepository = expensesRepository;
        this.earningsRepository = earningsRepository;

        //Initialize all repos
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Methods to run.

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

        User user3 = new User();
        user3.setUsername("Kristian");
        user3.setPassword("stor beaver");
        user3.setEmail("krille@mail.com");
        userRepository.save(user3);

        User user4 = new User();
        user4.setUsername("jon");
        user4.setPassword("dengru");
        user4.setEmail("joon@mail.com");
        userRepository.save(user4);

        Category category1 = new Category();
        category1.setName("Salary");
        category1.setType(Category.Type.earning);
        categoryRepository.save(category1);

        // Create category 2
        Category category2 = new Category();
        category2.setName("Rent");
        category2.setType(Category.Type.expense);
        categoryRepository.save(category2);

        // Create category 3
        Category category3 = new Category();
        category3.setName("Investments");
        category3.setType(Category.Type.earning);
        categoryRepository.save(category3);

        // Create category 4
        Category category4 = new Category();
        category4.setName("Utilities");
        category4.setType(Category.Type.expense);
        categoryRepository.save(category4);

        // Create category 5
        Category category5 = new Category();
        category5.setName("Groceries");
        category5.setType(Category.Type.expense);
        categoryRepository.save(category5);

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

        // Create subcategory 3 linked to Utilities
        Subcategory electricity = new Subcategory();
        electricity.setUser(user2);
        electricity.setName("Electricity");
        electricity.setCategory(category3);
        subcategoryRepository.save(electricity);

        // Create subcategory 4 linked to Groceries
        Subcategory freshProduce = new Subcategory();
        freshProduce.setUser(user1);
        freshProduce.setName("Fresh Produce");
        freshProduce.setCategory(category4);
        subcategoryRepository.save(freshProduce);

        // Create subcategory 5 linked to Salary
        Subcategory generalInvestments = new Subcategory();
        generalInvestments.setUser(user2);
        generalInvestments.setName("General Investments");
        generalInvestments.setCategory(category1);
        subcategoryRepository.save(generalInvestments);

        // Create expense 1 for user1 on Rent
        Expense expense1 = new Expense();
        expense1.setUser(user1);
        expense1.setSubcategory(apartmentRent);
        expense1.setAmount(1200.00);
        expense1.setDate(new Date()); // Today's date
        expensesRepository.save(expense1);

        // Create expense 2 for user2 on Electricity
        Expense expense2 = new Expense();
        expense2.setUser(user2);
        expense2.setSubcategory(apartmentRent);
        expense2.setAmount(300.00);
        expense2.setDate(new Date());
        expensesRepository.save(expense2);

        // Create expense 3 for user3 on Groceries
        Expense expense3 = new Expense();
        expense3.setUser(user3);
        expense3.setSubcategory(freshProduce);
        expense3.setAmount(150.00);
        expense3.setDate(new Date());
        expensesRepository.save(expense3);

        // Create earning 1 for user1 with Bonus
        Earning earning1 = new Earning();
        earning1.setUser(user1);
        earning1.setSubcategory(bonus);
        earning1.setAmount(2500.00);
        earning1.setDate(new Date()); // Today's date
        earningsRepository.save(earning1);

        // Create earning 2 for user2 with General Investments
        Earning earning2 = new Earning();
        earning2.setUser(user2);
        earning2.setSubcategory(electricity);
        earning2.setAmount(750.00);
        earning2.setDate(new Date());
        earningsRepository.save(earning2);

        //copy of earning 1 for user2
        Earning earning3 = new Earning();
        earning3.setUser(user2);
        earning3.setSubcategory(bonus);
        earning3.setAmount(2500.00);
        earning3.setDate(new Date());
        earningsRepository.save(earning3);

        // Create earning 4 for user2 with General Investments
        Earning earning4 = new Earning();
        earning4.setUser(user2);
        earning4.setSubcategory(generalInvestments);
        earning4.setAmount(750.00);
        earning4.setDate(new Date());
        earningsRepository.save(earning4);



    }

}

