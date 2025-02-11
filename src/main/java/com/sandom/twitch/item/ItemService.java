package com.sandom.twitch.item;

import com.sandom.twitch.TwitchApiClient;
import com.sandom.twitch.database.Assembly;
import com.sandom.twitch.model.Stream;
import com.sandom.twitch.model.Video;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private final TwitchApiClient twitchApiClient;
    public ItemService(TwitchApiClient twitchApiClient) {
        this.twitchApiClient = twitchApiClient;
    }

    @Cacheable("items")
    public Assembly getItems(String gameId) {
        List<Stream> streams = twitchApiClient.getStreams(List.of(gameId)).data();
        List<Video> videos = twitchApiClient.getVideos(gameId).data();
        return new Assembly("", streams, videos);
    }
    @Cacheable("items_by_list")
    public Assembly getItems(List<String> gameIds) {
        List<Stream> streams = twitchApiClient.getStreams(gameIds).data();
        List<Video> videos = new ArrayList<>();
        for (String gameId : gameIds) {
            videos.addAll(twitchApiClient.getVideos(gameId).data());
        }
        return new Assembly("", streams, videos);
    }
}
