package com.sandom.twitch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Game(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("box_art_url") String boxArtUrl
) {
}
