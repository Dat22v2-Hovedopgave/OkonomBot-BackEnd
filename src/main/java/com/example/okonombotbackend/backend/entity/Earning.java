package com.example.okonombotbackend.backend.entity;

import com.example.okonombotbackend.security.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "earnings")
public class Earning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    private Double amount;
    private Date date = new Date();

    // Getters and Setters
}

