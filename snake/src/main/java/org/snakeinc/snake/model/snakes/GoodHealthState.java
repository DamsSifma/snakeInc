package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.Direction;
import org.snakeinc.snake.model.food.Apple;
import org.snakeinc.snake.model.food.Food;

public class GoodHealthState implements HealthState {

    @Override
    public Direction adjustDirection(Direction direction) {
        return direction;
    }

    @Override
    public HealthState onEat(Food food) {
        switch (food) {
            case Apple apple -> {
                return apple.isPoisoned() ? new PoisonedState() : this;
            }
            default -> {
                return this;
            }
        }
    }
}
