package org.snakeinc.snake.model.food.strategy;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;
import org.snakeinc.snake.GameParams;

import java.util.Random;

public class EasyPlacementStrategy implements FoodPlacementStrategy {
    private final Random random = new Random();

    @Override
    public Cell generateCell(Snake snake, Grid grid) {
        // Obtenir la position de la tête du serpent
        Cell headCell = snake.getHead();
        Cell cell;

        do {
            int deltaX = random.nextInt(-3, 4);
            int deltaY = random.nextInt(-3, 4);

            int x = Math.max(0, Math.min(GameParams.TILES_X - 1, headCell.getX() + deltaX));
            int y = Math.max(0, Math.min(GameParams.TILES_Y - 1, headCell.getY() + deltaY));

            cell = grid.getTile(x, y);
        } while (cell.containsASnake() || cell.containsAFood());

        return cell;
    }
}