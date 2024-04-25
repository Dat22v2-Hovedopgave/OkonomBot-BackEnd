package com.example.okonombotbackend.repository;

import com.example.okonombotbackend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense, Integer> {

}
