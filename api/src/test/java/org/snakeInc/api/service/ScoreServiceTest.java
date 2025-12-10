package org.snakeInc.api.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snakeInc.api.model.dto.PlayerDto;
import org.snakeInc.api.model.dto.ScoreDto;
import org.snakeInc.api.model.entity.Player;
import org.snakeInc.api.model.entity.Score;
import org.snakeInc.api.repository.PlayerRepository;
import org.snakeInc.api.repository.ScoreRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    @InjectMocks
    private ScoreService scoreService;
    @Mock
    private ScoreRepository scoreRepository;
    @Mock
    private PlayerRepository playerRepository;
    Score score;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createScore_ShouldReturnScore() {
        LocalDateTime now = LocalDateTime.now();
        Player player = new Player("John", 25);
        player.setId(1);
        ScoreDto scoreDto = ScoreDto.builder()
                .snake("anaconda")
                .playerId(1)
                .score(100)
                .build();
        Score expectedScore = new Score("anaconda", 100, player);
        expectedScore.setPlayedAt(scoreDto.getPlayedAt());

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(scoreRepository.save(any(Score.class))).thenReturn(expectedScore);

        Score createdScore = scoreService.createScore(scoreDto);

        assertNotNull(createdScore);
        assertEquals("anaconda", createdScore.getSnake());
        assertEquals(100, createdScore.getScore());
    }
}