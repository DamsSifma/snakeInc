package org.snakeInc.api.model;

import jakarta.persistence.*;
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

