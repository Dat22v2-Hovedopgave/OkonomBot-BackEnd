package com.example.okonombotbackend.repository;

import com.example.okonombotbackend.entity.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarningsRepository extends JpaRepository<Earning, Integer> {
}
