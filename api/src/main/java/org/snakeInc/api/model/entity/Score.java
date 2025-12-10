package org.snakeInc.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Snake name is required")
    private String snake;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be negative")
    private Integer score;

    @NotNull(message = "Played at is required")
    private LocalDateTime playedAt;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Score(String snake, Integer points, Player player) {
        this.snake = snake;
        this.score = points;
        this.playedAt = LocalDateTime.now();
        this.player = player;
    }
}