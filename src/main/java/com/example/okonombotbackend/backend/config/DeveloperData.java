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

    // Repositories
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
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create users
        User user1 = new User("Adolf", "yippy", "takemeback@mail.com");
        userRepository.save(user1);

        User user2 = new User("Ferhat", "yippy", "pappaspizza@gmail.com");
        userRepository.save(user2);

        User user3 = new User("Kristian", "stor beaver", "krille@mail.com");
        userRepository.save(user3);

        User user4 = new User("jon", "dengru", "joon@mail.com");
        userRepository.save(user4);

        // Create categories
        createCategory("Job", Category.Type.earning);
        createCategory("Hjem", Category.Type.expense);
        createCategory("Investeringer", Category.Type.earning);
        createCategory("Forsyninger", Category.Type.expense);
        createCategory("Indkøb", Category.Type.expense);
        createCategory("Transport", Category.Type.expense);
        createCategory("Fritid", Category.Type.expense);
        createCategory("Sundhed", Category.Type.expense);
        createCategory("Forsikring", Category.Type.expense);
        createCategory("Uddannelse", Category.Type.expense);
        createCategory("Børn", Category.Type.expense);
        createCategory("Diverse", Category.Type.expense);
        createCategory("Gæld", Category.Type.expense);
        createCategory("Renter", Category.Type.earning);
        createCategory("Andet", Category.Type.earning);

        // Create expenses for user2
        createExpense(user2, "Husleje", 7000.00, "Hjem");
        createExpense(user2, "El", 500.00, "Hjem");
        createExpense(user2, "Vand", 300.00, "Hjem");
        createExpense(user2, "Internet", 300.00, "Hjem");
        createExpense(user2, "Busbillet", 300.00, "Transport");
        createExpense(user2, "Benzin", 1500.00, "Transport");
        createExpense(user2, "Bilvedligeholdelse", 2000.00, "Transport");
        createExpense(user2, "Husforsikring", 1200.00, "Forsikring");
        createExpense(user2, "Bilforsikring", 1800.00, "Forsikring");
        createExpense(user2, "Sundhedsforsikring", 1500.00, "Forsikring");
        createExpense(user2, "Dagpleje", 3000.00, "Børn");
        createExpense(user2, "Legetøj", 800.00, "Børn");
        createExpense(user2, "Børnetøj", 1500.00, "Børn");
        createExpense(user2, "Studielån", 2000.00, "Gæld");
        createExpense(user2, "Personligt lån", 3500.00, "Gæld");

        // Create earnings for user2
        createEarning(user2, "Bonus", 5000.00, "Job");
        createEarning(user2, "Aktier", 2000.00, "Investeringer");
        createEarning(user2, "Løn", 35000.00, "Job");
        createEarning(user2, "Freelance indkomst", 15000.00, "Job");
    }

    private void createCategory(String name, Category.Type type) {
        Category category = new Category();
        category.setName(name);
        category.setType(type);
        categoryRepository.save(category);
    }

    private void createExpense(User user, String subcategoryName, double amount, String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        Subcategory subcategory = subcategoryRepository.findByNameAndCategory(subcategoryName, category);

        if (subcategory == null) {
            subcategory = new Subcategory();
            subcategory.setUser(user);
            subcategory.setName(subcategoryName);
            subcategory.setCategory(category);
            subcategoryRepository.save(subcategory);
        }

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setSubcategory(subcategory);
        expense.setAmount(amount);
        expense.setDate(new Date());
        expensesRepository.save(expense);
    }

    private void createEarning(User user, String subcategoryName, double amount, String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        Subcategory subcategory = subcategoryRepository.findByNameAndCategory(subcategoryName, category);

        if (subcategory == null) {
            subcategory = new Subcategory();
            subcategory.setUser(user);
            subcategory.setName(subcategoryName);
            subcategory.setCategory(category);
            subcategoryRepository.save(subcategory);
        }

        Earning earning = new Earning();
        earning.setUser(user);
        earning.setSubcategory(subcategory);
        earning.setAmount(amount);
        earning.setDate(new Date());
        earningsRepository.save(earning);
    }
}
