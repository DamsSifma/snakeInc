package org.snakeinc.snake.model.food;

import org.snakeinc.snake.model.Cell;

public class FoodFactory {

    public static Food createFoodInCell(Cell cell, FoodType foodType) {
        return switch (foodType) {
            case APPLE -> createAppleInCell(cell);
            case BROCCOLI -> createBroccoliInCell(cell);
        };
    }

    public static Apple createAppleInCell(Cell cell) {
        Apple apple = new Apple();
        cell.addFood(apple);
        return apple;
    }

    public static Broccoli createBroccoliInCell(Cell cell) {
        Broccoli broccoli = new Broccoli();
        cell.addFood(broccoli);
        return broccoli;
    }

}
