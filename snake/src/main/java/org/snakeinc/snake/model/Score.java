package org.snakeinc.snake.model;

import lombok.Getter;

@Getter
public class Score {
    private int points;

    public Score() {
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
