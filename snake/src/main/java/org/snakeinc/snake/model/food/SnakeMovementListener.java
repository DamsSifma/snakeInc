package org.snakeinc.snake.model.food;

import org.snakeinc.snake.model.Cell;

public interface SnakeMovementListener {
    void onSnakeMoved(Cell headPosition);
}
