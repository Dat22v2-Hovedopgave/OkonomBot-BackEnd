package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.backend.dto.SubcategoryResponse;
import com.example.okonombotbackend.backend.entity.Subcategory;
import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    Subcategory findSubcategoryById(int subcategoryId);

    void deleteByIdAndAndUser(int subcategoryId, User user);
    List<Subcategory> findAllByUserUsername(String username);
}
