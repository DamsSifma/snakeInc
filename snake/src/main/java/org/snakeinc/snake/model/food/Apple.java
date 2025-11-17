package org.snakeinc.snake.model.food;

import lombok.Getter;

import java.util.Random;

@Getter
public final class Apple extends Food {
    private final boolean poisoned;

    public Apple() {
        var random = new Random();
        // 20% chance to be poisoned
        this.poisoned = random.nextInt(0, 100) < 20;
    }
}
