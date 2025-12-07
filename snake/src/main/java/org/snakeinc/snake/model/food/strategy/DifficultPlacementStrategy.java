package org.snakeinc.snake.model.food.strategy;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;
import org.snakeinc.snake.GameParams;

import java.util.Random;

public class DifficultPlacementStrategy implements FoodPlacementStrategy {
    private final Random random = new Random();

    @Override
    public Cell generateCell(Snake snake, Grid grid) {
        Cell cell;

        do {
            int side = random.nextInt(4);
            int x, y;

            switch (side) {
                case 0 -> { // Gauche
                    x = random.nextInt(2);
                    y = random.nextInt(GameParams.TILES_Y);
                }
                case 1 -> { // Droite
                    x = GameParams.TILES_X - 1 - random.nextInt(2);
                    y = random.nextInt(GameParams.TILES_Y);
                }
                case 2 -> { // Haut
                    x = random.nextInt(GameParams.TILES_X);
                    y = random.nextInt(2);
                }
                default -> { // Bas
                    x = random.nextInt(GameParams.TILES_X);
                    y = GameParams.TILES_Y - 1 - random.nextInt(2);
                }
            }

            cell = grid.getTile(x, y);
        } while (cell.containsASnake() || cell.containsAFood());

        return cell;
    }
}