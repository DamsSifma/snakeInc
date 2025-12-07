package org.snakeinc.snake.model.snakes;

import java.util.ArrayList;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.*;
import org.snakeinc.snake.model.food.Apple;
import org.snakeinc.snake.model.food.Broccoli;
import org.snakeinc.snake.model.food.Food;
import org.snakeinc.snake.model.food.FoodEatenListener;

public abstract sealed class Snake permits Anaconda, Python, BoaConstrictor {

    protected final ArrayList<Cell> body;
    protected final FoodEatenListener onFoodEatenListener;
    private final Grid grid;
    private HealthState healthState = new GoodHealthState();

    public Snake(FoodEatenListener listener, Grid grid) {
        this.body = new ArrayList<>();
        this.onFoodEatenListener = listener;
        this.grid = grid;
        for (int i = 0; i < GameParams.SNAKE_INITIAL_SIZE; i++) {
            Cell bodyPart = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
            bodyPart.addSnake(this);
            body.add(bodyPart);
        }
    }

    public int getSize() {
        return body.size();
    }

    public Cell getHead() {
        return body.getFirst();
    }

    public HealthState getHealthState() {
        return healthState;
    }

    public void eat(Food food, Cell cell) {
        switch (food) {
            case Apple apple -> eatApple(cell);
            case Broccoli broccoli -> eatBroccoli(cell);
            default -> throw new IllegalStateException("Unexpected food: " + food);
        }
        healthState = healthState.onEat(food);
        onFoodEatenListener.onFoodEaten(food, cell);
    }

    public abstract void eatBroccoli(Cell cell);

    public abstract void eatApple(Cell cell);

    public void move(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        int x = getHead().getX();
        int y = getHead().getY();
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        Cell newHead = grid.getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.containsASnake()) {
            throw new SelfCollisionException();
        }

        // Eat apple :
        if (newHead.containsAFood()) {
            this.eat(newHead.getFood(), newHead);
            if (body.isEmpty()) {
                throw new MalnutritionException();
            }
            return;
        }

        // The snake did not eat :
        newHead.addSnake(this);
        body.addFirst(newHead);

        body.getLast().removeSnake();
        body.removeLast();

    }

    public SnakeColor getColor() {
        return SnakeColor.PINK;
    }
}
