package org.snakeInc.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snakeInc.api.model.Player;
import org.snakeInc.api.model.PlayerParams;
import org.snakeInc.api.repository.PlayerRepository;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;
    @Mock
    private PlayerRepository playerRepository;
    private Player player;

    @BeforeEach()
    public void setUp() {
        PlayerParams playersParams = new PlayerParams();
        playersParams.setName("TestPlayer");
        playersParams.setAge(20);
    }

    @Test
    public void createPlayer_ShouldReturnPlayer() {
        PlayerParams playersParams = new PlayerParams();
        playersParams.setName("TestPlayer");
        playersParams.setAge(20);
        Player expectedPlayer = new Player("TestPlayer", 20);

        when(playerRepository.save(any())).thenReturn(expectedPlayer);

        Player createdPlayer = playerService.create(playersParams);

        Assertions.assertEquals(expectedPlayer, createdPlayer);
    }

    @Test
    public void getPlayerById_ShouldReturnPlayer() {
        PlayerParams playersParams = new PlayerParams();
        playersParams.setName("TestPlayer");
        playersParams.setAge(20);
        Player expectedPlayer = new Player("TestPlayer", 20);

        expectedPlayer = new Player("TestPlayer", 20);
        when(playerRepository.save(any())).thenReturn(expectedPlayer);
        when(playerRepository.findById(any())).thenReturn(Optional.of(expectedPlayer));

        Player createdPlayer = playerService.create(playersParams);
        Player foundPlayer = playerService.findById(createdPlayer.getId());

        Assertions.assertEquals(createdPlayer, foundPlayer);
    }
}