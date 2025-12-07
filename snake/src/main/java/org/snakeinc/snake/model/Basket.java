package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.food.FoodFactory;
import org.snakeinc.snake.model.food.FoodType;
import org.snakeinc.snake.model.food.strategy.FoodPlacementStrategy;
import org.snakeinc.snake.model.snakes.Snake;

@Data
public class Basket {

    private Grid grid;
    private List<Food> foods;
    private FoodPlacementStrategy placementStrategy;

    public Basket(Grid grid, FoodPlacementStrategy placementStrategy) {
        foods = new ArrayList<>();
        this.grid = grid;
        this.placementStrategy = placementStrategy;
    }

    public void addFood(Cell cell, FoodType foodType, Snake snake) {
        if (cell == null) {
            cell = placementStrategy.generateCell(snake, grid);
        }
        Food food = FoodFactory.createFoodInCell(cell, foodType);
        foods.add(food);
    }

    public void removeFoodInCell(Food food, Cell cell) {
        cell.removeFood();
        foods.remove(food);
    }

    public boolean isEmpty() {
        return foods.isEmpty();
    }

    private void refill(int nFood, Snake snake) {
        for (int i = 0; i < nFood; i++) {
            addFood(null, getRandomFoodType(), snake);
        }
    }
    private FoodType getRandomFoodType() {
        // get random food between apple and broccoli
        var random = new Random();
        int foodType = random.nextInt(0, 2);
        if (foodType == 0) {
            return FoodType.APPLE;
        } else {
            return FoodType.BROCCOLI;
        }
    }

    public void refillIfNeeded(int nApples, Snake snake) {
        int missingApple = nApples - foods.size();
        if (missingApple > 0) {
            refill(missingApple, snake);
        }
    }

}
