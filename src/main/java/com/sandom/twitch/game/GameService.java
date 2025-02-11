package com.sandom.twitch.game;

import com.sandom.twitch.TwitchApiClient;
import com.sandom.twitch.model.Game;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final TwitchApiClient twitchApiClient;
    public GameService(TwitchApiClient twitchApiClient) {
        this.twitchApiClient = twitchApiClient;
    }

    @Cacheable("top_games")
    public List<Game> getTopGames() {
        return twitchApiClient.getTopGames().data();
    }
    @Cacheable("games")
    public List<Game> getGames(String gameName) {
        return twitchApiClient.getGames(gameName).data();
    }
}
