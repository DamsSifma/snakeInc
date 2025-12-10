package org.snakeInc.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snakeInc.api.model.Player;
import org.snakeInc.api.model.PlayerParams;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

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

        Player createdPlayer = playerService.create(playersParams);

        Assertions.assertNotNull(createdPlayer);
        Assertions.assertEquals("TestPlayer", createdPlayer.getName());
        Assertions.assertEquals(20, createdPlayer.getAge());
    }

    @Test
    public void getPlayerById_ShouldReturnPlayer() {
        PlayerParams playersParams = new PlayerParams();
        playersParams.setName("TestPlayer");
        playersParams.setAge(20);

        Player createdPlayer = playerService.create(playersParams);
        Player foundPlayer = playerService.findById(createdPlayer.getId());

        Assertions.assertEquals(createdPlayer, foundPlayer);
    }
}