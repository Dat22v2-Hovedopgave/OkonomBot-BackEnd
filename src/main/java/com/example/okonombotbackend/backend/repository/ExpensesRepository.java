package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {

  boolean existsByUserAndSubcategoryId(User userByUsername, int subcategoryId);

  Expense findByUserAndSubcategoryId(User userByUsername, int subcategoryId);
}
