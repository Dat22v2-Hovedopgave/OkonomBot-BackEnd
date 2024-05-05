package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.dto.earning.EarningResponse;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface EarningsRepository extends JpaRepository<Earning, Integer> {

    boolean existsByUserAndSubcategoryId(User user, int subcategoryId);

    Earning findByUserAndSubcategoryId(User user, int subcategoryId);
}
