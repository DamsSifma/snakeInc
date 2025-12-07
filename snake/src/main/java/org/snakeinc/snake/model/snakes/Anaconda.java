package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.food.FoodEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

public final class Anaconda extends Snake {
    public Anaconda(FoodEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public SnakeColor getColor() {
        return SnakeColor.GRAY;
    }

    public void eatApple(Cell cell) {
        body.addFirst(cell);
        cell.addSnake(this);
    }

    public void eatBroccoli(Cell cell) {
        for (int i = 0; i < 2; i++) {
            if (body.isEmpty()) {
                break;
            }
            var cellTail = body.removeLast();
            cellTail.removeSnake();
        }
    }
}
