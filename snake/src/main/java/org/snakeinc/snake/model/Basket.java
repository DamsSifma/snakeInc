package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.food.FoodFactory;
import org.snakeinc.snake.model.food.FoodType;

@Data
public class Basket {

    private Grid grid;
    private List<Food> foods;

    public Basket(Grid grid) {
        foods = new ArrayList<>();
        this.grid = grid;
    }

    public void addFood(Cell cell, FoodType foodType) {
        if (cell == null) {
            var random = new Random();
            cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
        }
        Food food = FoodFactory.createFoodInCell(cell, foodType);
        foods.add(food);
    }

    private Cell findRandomFreeCell() {
        var random = new Random();
        Cell cell = null;
        while (cell.containsASnake() || cell.containsAFood()) {
            cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
        }
        return cell;
    }

    public void removeFoodInCell(Food food, Cell cell) {
        cell.removeFood();
        foods.remove(food);
    }

    public boolean isEmpty() {
        return foods.isEmpty();
    }

    private void refill(int nFood) {
        for (int i = 0; i < nFood; i++) {
            addFood(null, getRandomFoodType());
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

    public void refillIfNeeded(int nApples) {
        int missingApple = nApples - foods.size();
        if (missingApple > 0) {
            refill(missingApple);
        }
    }

}
