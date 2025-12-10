package org.snakeInc.api.controller;


import jakarta.validation.Valid;
import org.snakeInc.api.model.dto.PlayerDto;
import org.snakeInc.api.model.entity.Player;

import org.snakeInc.api.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping()
    public Player CreatePlayer(@Valid @RequestBody PlayerDto playerParams) {
        return playerService.create(playerParams);
    }

    @GetMapping("/{id}")
    public Player GetPlayer(@PathVariable Integer id) {
        return playerService.findById(id);
    }

}
