package org.snakeinc.snake.model.food.strategy;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;

public interface FoodPlacementStrategy {
    Cell generateCell(Snake snake, Grid grid);
}