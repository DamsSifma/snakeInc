package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.food.Apple;
import org.snakeinc.snake.model.food.Broccoli;
import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.food.FoodEatenListener;
import org.snakeinc.snake.model.food.strategy.DifficultPlacementStrategy;
import org.snakeinc.snake.model.food.strategy.EasyPlacementStrategy;
import org.snakeinc.snake.model.food.strategy.FoodPlacementStrategy;
import org.snakeinc.snake.model.food.strategy.RandomPlacementStrategy;
import org.snakeinc.snake.model.snakes.*;

import java.util.Random;

@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private final Snake snake;
    private final SnakeType type;
    private final Score score;

    public Game() {
        grid = new Grid();
        score = new Score();

        // Choisir directement une stratégie aléatoire
        Random random = new Random();
        FoodPlacementStrategy strategy = switch (random.nextInt(3)) {
            case 0 -> new RandomPlacementStrategy();
            case 1 -> new EasyPlacementStrategy();
            case 2 -> new DifficultPlacementStrategy();
            default -> throw new IllegalStateException();
        };

        basket = new Basket(grid, strategy);

        SnakeType[] types = SnakeType.values();
        type = types[new Random().nextInt(types.length)];
        FoodEatenListener removeFoodAndUpdateScore = (food, cell) -> {
            basket.removeFoodInCell(food, cell);
            updateScore(food);
        };
        snake = switch (type) {
            case ANACONDA -> new Anaconda(removeFoodAndUpdateScore, grid);
            case PYTHON -> new Python(removeFoodAndUpdateScore, grid);
            case BOA_CONSTRICTOR -> new BoaConstrictor(removeFoodAndUpdateScore, grid);
        };

        basket.refillIfNeeded(5, snake);
    }

    public void iterate(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        snake.move(direction);
        basket.refillIfNeeded(5, snake);
    }

    private void updateScore(Food food) {
        switch (food) {
            case Apple apple -> {
                if (!apple.isPoisoned()) {
                    score.addPoints(2);
                } else {
                    score.addPoints(0);
                }
            }
            case Broccoli broccoli -> {
                if (!broccoli.isSteamed()) {
                    score.addPoints(1);
                } else {
                    score.addPoints(0);
                }
            }
            default -> {}
        }
    }
}
