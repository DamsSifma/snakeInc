package org.snakeInc.api.model.entity;

public enum Category {
    JUNIOR,
    SENIOR;

    public static Category fromAge(int age) {
        return age >= 18 ? SENIOR : JUNIOR;
    }
}