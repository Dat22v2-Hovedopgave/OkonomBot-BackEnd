package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {

}
