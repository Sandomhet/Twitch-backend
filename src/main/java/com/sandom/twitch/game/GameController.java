package com.sandom.twitch.game;

import com.sandom.twitch.model.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping
public class GameController {
    private final GameService gameService;
    public GameController (GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public List<Game> getGames(@RequestParam(value = "game_name", required = false) String gameName) {
        if (gameName == null) {
            return gameService.getTopGames();
        }
        return gameService.getGames(gameName);
    }
}
