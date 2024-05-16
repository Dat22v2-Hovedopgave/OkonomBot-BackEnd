package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EarningsRepository extends JpaRepository<Earning, Integer> {

    boolean existsByUsersAndSubcategoryId(Users users, int subcategoryId);

    Earning findByUsersAndSubcategoryId(Users users, int subcategoryId);

    Earning findEarningById(int earningId);
}
