package org.snakeinc.snake.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScoreDto {
    @JsonProperty("playerId")
    private Integer playerId;
    @JsonProperty("snake")
    private String snake;
    @JsonProperty("score")
    private int score;

    public ScoreDto() {}

    public ScoreDto(Integer playerId, String snake, int score) {
        this.playerId = playerId;
        this.snake = snake;
        this.score = score;
    }

}

