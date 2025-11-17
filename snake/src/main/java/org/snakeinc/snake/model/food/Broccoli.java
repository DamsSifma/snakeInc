package org.snakeinc.snake.model.food;

import lombok.Getter;

import java.util.Random;

@Getter
public final class Broccoli extends Food {
    private final boolean steamed;

    public Broccoli() {
        var random = new Random();
        // 30% chance to be steamed
        this.steamed = random.nextInt(0, 100) < 30;
    }
}
