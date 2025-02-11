package com.sandom.twitch.database;

import com.sandom.twitch.database.entity.Item;
import com.sandom.twitch.model.Stream;
import com.sandom.twitch.model.Video;

import java.util.ArrayList;
import java.util.List;

public record Assembly(
        List<Item> streams,
        List<Item> videos
) {
    public Assembly() {
        this(new ArrayList<>(), new ArrayList<>());
    }
    public Assembly(List<Item> items) {
        this(filters(items, ItemType.STREAM), filters(items, ItemType.VIDEO));
    }
    public Assembly(String gameId, List<Stream> streams, List<Video> videos) {
        this(forStreams(streams), forVideos(videos));
    }

    private static List<Item> filters(List<Item> items, ItemType type) {
        List<Item> filtered = new ArrayList<>();
        for (Item item: items) {
            if (item.itemType() == type) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    private static List<Item> forStreams(List<Stream> streams) {
        List<Item> transformed = new ArrayList<>();
        for (Stream stream: streams) {
            transformed.add(new Item(stream));
        }
        return transformed;
    }
    private static List<Item> forVideos(List<Video> videos) {
        List<Item> transformed = new ArrayList<>();
        for (Video video: videos) {
            transformed.add(new Item(video));
        }
        return transformed;
    }

    public void addAll(Assembly other) {
        this.streams.addAll(other.streams);
        this.videos.addAll(other.videos);
    }
}
