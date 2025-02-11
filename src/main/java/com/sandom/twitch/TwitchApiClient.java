package com.sandom.twitch;

import com.sandom.twitch.model.GameResponse;
import com.sandom.twitch.model.StreamResponse;
import com.sandom.twitch.model.VideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "twitch-api")
public interface TwitchApiClient {
    @GetMapping("/games/top")
    GameResponse getTopGames();
    @GetMapping("/games")
    GameResponse getGames(@RequestParam("name") String name);

    @GetMapping("/streams")
    StreamResponse getStreams(@RequestParam("game_id") List<String> gameIds);
    @GetMapping("/videos")
    VideoResponse getVideos(@RequestParam("game_id") String gameId);
}
