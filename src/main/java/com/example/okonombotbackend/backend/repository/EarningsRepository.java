package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.entity.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningsRepository extends JpaRepository<Earning, Integer> {
}
