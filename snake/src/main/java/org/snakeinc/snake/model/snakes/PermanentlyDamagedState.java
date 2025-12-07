package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.Direction;
import org.snakeinc.snake.model.food.Food;

public class PermanentlyDamagedState implements HealthState {
    @Override
    public Direction adjustDirection(Direction direction) {
        return switch (direction) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }

    @Override
    public HealthState onEat(Food food) {
        return this;
    }
}