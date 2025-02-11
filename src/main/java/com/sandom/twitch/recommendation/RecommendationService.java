package com.sandom.twitch.recommendation;

import com.sandom.twitch.database.Assembly;
import com.sandom.twitch.database.entity.Favorite;
import com.sandom.twitch.database.entity.Item;
import com.sandom.twitch.database.entity.UserEntity;
import com.sandom.twitch.database.repository.FavoriteRepository;
import com.sandom.twitch.database.repository.UserRepository;
import com.sandom.twitch.favorite.FavoriteService;
import com.sandom.twitch.game.GameService;
import com.sandom.twitch.item.ItemService;
import com.sandom.twitch.model.Game;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationService {
    private final FavoriteService favoriteService;
    private final GameService gameService;
    private final ItemService itemService;

    public RecommendationService(FavoriteService favoriteService, GameService gameService, ItemService itemService) {
        this.favoriteService = favoriteService;
        this.gameService = gameService;
        this.itemService = itemService;
    }

    /*
    get favorite items' game id
    recommend all the game items
    exclude items that already in favorite
    */
    @Cacheable("recommend_items")
    public Assembly getRecommendation(UserEntity user) {
        List<Game> topGames = new ArrayList<>();
        List<Item> favoriteItems = new ArrayList<>();
        if (user == null) { // unregistered user
            topGames = gameService.getTopGames();
        } else {
            favoriteItems = favoriteService.getFavoriteItems(user);
            if (favoriteItems.isEmpty()) { // new user who doesn't have favorites
                topGames = gameService.getTopGames();
            }
        }
        Set<String> gameIds = new HashSet<>(); // prevent duplicate gameIds
        for (Game game : topGames) {
            gameIds.add(game.id());
        }
        for (Item item : favoriteItems) {
            gameIds.add(item.gameId());
        }
        return itemService.getItems(new ArrayList<>(gameIds));
    }
}
