package org.snakeInc.api.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScoreDto {
    @NotBlank(message = "Snake name is required")
    private String snake;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be negative")
    private Integer score;

    private LocalDateTime playedAt = LocalDateTime.now();

    @NotNull(message = "Player ID is required")
    private Integer playerId;
}