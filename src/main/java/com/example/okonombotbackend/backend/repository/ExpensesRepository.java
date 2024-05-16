package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {

  boolean existsByUsersAndSubcategoryId(Users usersByUsername, int subcategoryId);

  Expense findByUsersAndSubcategoryId(Users usersByUsername, int subcategoryId);
}
