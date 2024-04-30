package com.example.okonombotbackend.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        earning, expense
    }

    // Getters and Setters
}
