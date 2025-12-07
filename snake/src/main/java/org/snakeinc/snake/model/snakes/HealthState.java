package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.Direction;
import org.snakeinc.snake.model.food.Food;

public interface HealthState {
    Direction adjustDirection(Direction direction);
    HealthState onEat(Food food);
}
