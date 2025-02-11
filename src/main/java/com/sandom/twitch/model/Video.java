package com.sandom.twitch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Video(
        @JsonProperty("id") String id,
        @JsonProperty("stream_id") String streamId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("user_login") String userLogin,
        @JsonProperty("user_name") String userName,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("published_at") String publishedAt,
        @JsonProperty("url") String url,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        @JsonProperty("viewable") String viewable,
        @JsonProperty("view_count") Integer viewCount,
        @JsonProperty("language") String language,
        @JsonProperty("type") String type,
        @JsonProperty("duration") String duration
) {
}
