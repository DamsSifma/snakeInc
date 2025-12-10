package org.snakeInc.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class StatsResponseDto {
    private Integer playerId;
    private List<StatsDto> stats;
}