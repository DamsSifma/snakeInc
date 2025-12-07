package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.Direction;
import org.snakeinc.snake.model.food.Apple;
import org.snakeinc.snake.model.food.Broccoli;
import org.snakeinc.snake.model.food.Food;

public class PoisonedState implements HealthState {
    @Override
    public Direction adjustDirection(Direction direction) {
        return switch (direction) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            default -> direction;
        };
    }

    public HealthState onEat(Food food) {
        switch (food) {
            case Apple apple -> {
                return apple.isPoisoned() ? new PermanentlyDamagedState() : this;
            }
            case Broccoli broccoli -> {
                return new GoodHealthState();
            }
            default -> {
                return this;
            }
        }
    }

}