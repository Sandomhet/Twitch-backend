package com.sandom.twitch.database.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sandom.twitch.database.ItemType;
import com.sandom.twitch.model.Stream;
import com.sandom.twitch.model.Video;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("items")
public record Item(
        @Id Long id,
        @JsonProperty("twitch_id") String twitchId,
        @JsonProperty("game_id") String gameId,
        @JsonProperty("title") String title,
        @JsonProperty("url") String url,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        @JsonProperty("broadcaster_name") String broadcasterName,
        @JsonProperty("item_type") ItemType itemType
) {
    public Item(Stream stream) {
        this(null, stream.id(), stream.gameId(), stream.title(), null, stream.thumbnailUrl(), stream.userName(), ItemType.STREAM);
    }
    public Item(Video video) {
        this(null, video.id(), null, video.title(), video.url(), video.thumbnailUrl(), video.userName(), ItemType.VIDEO);
    }
}
