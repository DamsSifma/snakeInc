package org.snakeInc.api.controller;

import jakarta.validation.Valid;
import org.snakeInc.api.model.dto.ScoreDto;
import org.snakeInc.api.model.entity.Score;
import org.snakeInc.api.service.PlayerService;
import org.snakeInc.api.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {
    private final ScoreService scoreService;
    private final PlayerService playerService;

    public ScoreController(ScoreService scoreService, PlayerService playerService) {
        this.scoreService = scoreService;
        this.playerService = playerService;
    }

    @PostMapping()
    public Score CreateScore(@RequestBody @Valid ScoreDto params) {
        var player = playerService.findById(params.getPlayerId());
        if (player == null) {
            throw new IllegalArgumentException("Player with id " + params.getPlayerId() + " does not exist");
        }
        return scoreService.createScore(params);
    }

    @GetMapping()
    public List<Score> GetScores(
            @RequestParam(required = false) Integer playerId,
            @RequestParam(required = false) String snake) {
        return scoreService.getScores(playerId, snake);
    }
}
