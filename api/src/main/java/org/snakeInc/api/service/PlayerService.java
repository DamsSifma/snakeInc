package org.snakeInc.api.service;

import org.snakeInc.api.model.Player;
import org.snakeInc.api.model.PlayerParams;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private final AtomicInteger idGenerator = new AtomicInteger(0);
    private final Map<Integer, Player> players = new HashMap<>();

    public Player create(PlayerParams params) {
        int id = idGenerator.incrementAndGet();
        Player player = new Player(id, params.getName(), params.getAge());
        players.put(id, player);
        return player;
    }

    public Player findById(int id) {
        return players.get(id);
    }
}