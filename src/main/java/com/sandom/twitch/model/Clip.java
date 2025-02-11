package com.sandom.twitch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Clip(
        @JsonProperty("id") String id,
        @JsonProperty("url") String url,
        @JsonProperty("embed_url") String embedUrl,
        @JsonProperty("broadcaster_id") String broadcasterId,
        @JsonProperty("broadcaster_name") String broadcasterName,
        @JsonProperty("creator_id") String creatorId,
        @JsonProperty("creator_name") String creatorName,
        @JsonProperty("video_id") String videoId,
        @JsonProperty("game_id") String gameId,
        @JsonProperty("language") String language,
        @JsonProperty("title") String title,
        @JsonProperty("view_count") Integer viewCount,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        @JsonProperty("duration") Float duration,
        @JsonProperty("vod_offset") Integer vodOffset,
        @JsonProperty("is_featured") Boolean isFeatured
) {
}
