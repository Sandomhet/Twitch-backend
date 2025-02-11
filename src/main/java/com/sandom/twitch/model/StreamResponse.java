package com.sandom.twitch.model;

import java.util.List;

public record StreamResponse(
        List<Stream> data
) {
}
