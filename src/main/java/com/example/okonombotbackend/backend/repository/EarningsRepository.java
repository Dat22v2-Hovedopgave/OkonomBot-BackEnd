package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.dto.earning.EarningResponse;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface EarningsRepository extends JpaRepository<Earning, Integer> {
    Boolean existsByUserIdAndSubcategoryId(int userId, int subcategoryId);
    Earning findByUserIdAndSubcategoryId(int userId, int subcategoryId);
}
