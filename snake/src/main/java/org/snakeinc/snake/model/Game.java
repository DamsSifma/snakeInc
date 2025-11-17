package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
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
        basket = new Basket(grid);
        basket.refillIfNeeded(5);
        SnakeType[] types = SnakeType.values();
        type = types[new Random().nextInt(types.length)];
        snake = switch (type) {
            case ANACONDA -> new Anaconda(basket::removeFoodInCell, grid);
            case PYTHON -> new Python(basket::removeFoodInCell, grid);
            case BOA_CONSTRICTOR -> new BoaConstrictor(basket::removeFoodInCell, grid);
        };
    }

    public void iterate(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        snake.move(direction);
        basket.refillIfNeeded(1);
    }
}
