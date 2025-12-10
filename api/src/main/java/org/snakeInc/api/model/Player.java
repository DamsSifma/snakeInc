package org.snakeInc.api.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Player {
    private final int id;
    private final int age;
    private final String name;
    private final Category category;
    private final LocalDate createdAt = LocalDate.now();

    public Player(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.category = Category.fromAge(age);
    }

}

