package com.example.okonombotbackend.repository;

import com.example.okonombotbackend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {

}
