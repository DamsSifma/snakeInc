package org.snakeInc.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsDto {
    private String snake;
    private Integer min;
    private Integer max;
    private Double average;
}