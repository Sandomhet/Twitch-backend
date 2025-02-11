package com.sandom.twitch.item;

import com.sandom.twitch.database.Assembly;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/search")
    public Assembly getItems(@RequestParam("game_id") String gameId) {
        return itemService.getItems(gameId);
    }
}
