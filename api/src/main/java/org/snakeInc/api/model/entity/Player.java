package org.snakeInc.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int age;
    private String name;

    private Category category;

    private LocalDate createdAt = LocalDate.now();

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.category = Category.fromAge(age);
    }
}

