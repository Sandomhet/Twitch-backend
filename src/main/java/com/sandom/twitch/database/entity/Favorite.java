package com.sandom.twitch.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("favorite_records")
public record Favorite(
        @Id Long id,
        Long userId,
        Long itemId,
        Instant createdAt
) {
}
