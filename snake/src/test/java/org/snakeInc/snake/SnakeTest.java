package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.MalnutritionException;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Direction;
import org.snakeinc.snake.model.Game;

public class SnakeTest {

    Game game = new Game();

    @Test
    public void snakeEatApplesAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getSnake().move(Direction.UP);
        Assertions.assertEquals(4, game.getSnake().getSize());
    }

    public void boaEatsAppleAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getSnake().move(Direction.UP);
        Assertions.assertEquals(3, game.getSnake().getSize());
    }

    @Test
    void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        game.getSnake().move(Direction.UP);
        Assertions.assertEquals(5, game.getSnake().getHead().getX());
        Assertions.assertEquals(4, game.getSnake().getHead().getY());
    }

    @Test
    void snakeOutOfPlay_ThrowsOutOfPlayException() {
        Assertions.assertThrows(OutOfPlayException.class, () -> {
            for (int i = 0; i < GameParams.SNAKE_DEFAULT_Y + 1; i++) {
                game.iterate(Direction.UP);
            }
        }, "Expected to throw OutOfPlayException");
    }

    @Test
    void snakeSelfCollision_ThrowsSelfCollisionException() throws OutOfPlayException, SelfCollisionException, MalnutritionException {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getBasket().addApple(game.getGrid().getTile(5, 3));
        game.getBasket().addApple(game.getGrid().getTile(5, 2));
        game.iterate(Direction.UP);
        game.iterate(Direction.UP);
        game.iterate(Direction.UP);
        game.iterate(Direction.LEFT);
        game.iterate(Direction.DOWN);
        Assertions.assertThrows(SelfCollisionException.class, () -> {
            game.iterate(Direction.RIGHT);
        }, "Expected to throw SelfCollisionException");
    }
}
