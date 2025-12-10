package org.snakeInc.api.service;

import org.snakeInc.api.model.Player;
import org.snakeInc.api.model.PlayerParams;
import org.snakeInc.api.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player create(PlayerParams params) {
        Player player = new Player(params.getName(), params.getAge());
        return playerRepository.save(player);
    }

    public Player findById(int id) {
        return playerRepository.findById(id).orElse(null);
    }
}