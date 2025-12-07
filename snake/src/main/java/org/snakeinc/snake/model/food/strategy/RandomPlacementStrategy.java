package org.snakeinc.snake.model.food.strategy;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;
import org.snakeinc.snake.GameParams;

import java.util.Random;

public class RandomPlacementStrategy implements FoodPlacementStrategy {
    private final Random random = new Random();

    @Override
    public Cell generateCell(Snake snake, Grid grid) {
        Cell cell;
        do {
            int x = random.nextInt(GameParams.TILES_X);
            int y = random.nextInt(GameParams.TILES_Y);
            cell = grid.getTile(x, y);
        } while (cell.containsASnake() || cell.containsAFood());

        return cell;
    }
}