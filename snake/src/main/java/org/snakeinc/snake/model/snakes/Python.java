package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.food.Apple;
import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.food.FoodEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

public final class Python extends Snake {
    public Python(FoodEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eatApple(Cell cell) {
    }

    @Override
    public void eatBroccoli(Cell cell) {
        for (int i = 0; i < 3; i++) {
            if (body.isEmpty()) {
                break;
            }
            var cellTail = body.removeLast();
            cellTail.removeSnake();
        }
    }

    @Override
    public SnakeColor getColor() {
        return SnakeColor.GREEN;
    }
}
