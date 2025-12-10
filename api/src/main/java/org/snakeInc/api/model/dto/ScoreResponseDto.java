package org.snakeInc.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ScoreResponseDto {
    private List<ScoreDto> scores;
}