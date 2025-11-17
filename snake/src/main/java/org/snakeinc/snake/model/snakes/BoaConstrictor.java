package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.food.FoodEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

public final class BoaConstrictor extends Snake {
    public BoaConstrictor(FoodEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    @Override
    public void eatApple(Cell cell) {
        var cellTail = body.removeLast();
        cellTail.removeSnake();
    }

    public void eatBroccoli(Cell cell) {
    }

    @Override
    public SnakeColor getColor() {
        return SnakeColor.BLUE;
    }
}
