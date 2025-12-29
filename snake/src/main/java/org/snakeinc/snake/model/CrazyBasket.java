package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.food.*;
import org.snakeinc.snake.model.food.strategy.FoodPlacementStrategy;
import org.snakeinc.snake.model.snakes.Snake;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CrazyBasket extends Basket implements SnakeMovementListener {
    private final Map<Food, Cell> foodPositions = new HashMap<>();
    private final Random random = new Random();
    private static final int FLEE_DISTANCE = 3;
    private static final double FLEE_PROBABILITY = 0.1;

    public CrazyBasket(Grid grid, FoodPlacementStrategy placementStrategy) {
        super(grid, placementStrategy);
    }

    @Override
    public void addFood(Cell cell, FoodType foodType, Snake snake) {
        if (cell == null) {
            cell = getPlacementStrategy().generateCell(snake, getGrid());
        }
        super.addFood(cell, foodType, snake);
        Food addedFood = getFoods().getLast();
        foodPositions.put(addedFood, cell);
    }

    @Override
    public void removeFoodInCell(Food food, Cell cell) {
        foodPositions.remove(food);
        super.removeFoodInCell(food, cell);
    }

    @Override
    public void onSnakeMoved(Cell headPosition) {
        for (Map.Entry<Food, Cell> entry : new HashMap<>(foodPositions).entrySet()) {
            Food food = entry.getKey();
            Cell currentCell = entry.getValue();

            int distance = Math.abs(currentCell.getX() - headPosition.getX()) +
                    Math.abs(currentCell.getY() - headPosition.getY());

            if (distance <= FLEE_DISTANCE && random.nextDouble() < FLEE_PROBABILITY) {
                Cell newCell = findSafeCell(currentCell, headPosition);
                if (newCell != null) {
                    moveFood(food, currentCell, newCell);
                }
            }
        }
    }

    private void moveFood(Food food, Cell oldCell, Cell newCell) {
        oldCell.removeFood();
        newCell.addFood(food);
        foodPositions.put(food, newCell);
    }

    private Cell findSafeCell(Cell currentCell, Cell snakeHeadCell) {
        for (int attempt = 0; attempt < 10; attempt++) {
            int deltaX = random.nextInt(-2, 3);
            int deltaY = random.nextInt(-2, 3);

            int newX = Math.max(0, Math.min(GameParams.TILES_X - 1, currentCell.getX() + deltaX));
            int newY = Math.max(0, Math.min(GameParams.TILES_Y - 1, currentCell.getY() + deltaY));

            Cell candidate = getGrid().getTile(newX, newY);

            if (!candidate.containsASnake() && !candidate.containsAFood() &&
                    isFurtherFromSnake(currentCell, candidate, snakeHeadCell)) {
                return candidate;
            }
        }
        return null;
    }

    private boolean isFurtherFromSnake(Cell currentCell, Cell candidate, Cell snakeHeadCell) {
        int currentDistance = Math.abs(currentCell.getX() - snakeHeadCell.getX()) +
                Math.abs(currentCell.getY() - snakeHeadCell.getY());
        int newDistance = Math.abs(candidate.getX() - snakeHeadCell.getX()) +
                Math.abs(candidate.getY() - snakeHeadCell.getY());
        return newDistance > currentDistance;
    }
}
