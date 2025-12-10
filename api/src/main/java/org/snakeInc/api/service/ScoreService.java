package org.snakeInc.api.service;

import org.snakeInc.api.model.dto.ScoreDto;
import org.snakeInc.api.model.entity.Score;
import org.snakeInc.api.repository.PlayerRepository;
import org.snakeInc.api.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;

    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
    }

    public Score getScore(Integer id) {
        return scoreRepository.findById(id).orElse(null);
    }

    public Score createScore(ScoreDto dto) {
        var player = playerRepository.findById(dto.getPlayerId()).orElseThrow();
        Score score = new Score(dto.getSnake(), dto.getScore(), player);
        return scoreRepository.save(score);
    }

    public List<Score> getScores(Integer playerId, String snake) {
       return scoreRepository.findAllFiltered(playerId, snake);
    }
}
