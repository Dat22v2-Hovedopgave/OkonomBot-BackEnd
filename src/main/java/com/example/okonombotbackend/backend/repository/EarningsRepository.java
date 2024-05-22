package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EarningsRepository extends JpaRepository<Earning, Integer> {

    boolean existsByUserAndSubcategoryId(User user, int subcategoryId);

    Earning findByUserAndSubcategoryId(User user, int subcategoryId);

    Earning findEarningById(int earningId);
}
