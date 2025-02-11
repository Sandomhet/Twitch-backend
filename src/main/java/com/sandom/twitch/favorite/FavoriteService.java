package com.sandom.twitch.favorite;

import com.sandom.twitch.database.Assembly;
import com.sandom.twitch.database.entity.Favorite;
import com.sandom.twitch.database.entity.Item;
import com.sandom.twitch.database.entity.UserEntity;
import com.sandom.twitch.database.repository.FavoriteRepository;
import com.sandom.twitch.database.repository.ItemRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ItemRepository itemRepository;
    public FavoriteService(FavoriteRepository favoriteRepository, ItemRepository itemRepository) {
        this.favoriteRepository = favoriteRepository;
        this.itemRepository = itemRepository;
    }

    public List<Item> getFavoriteItems(UserEntity user) {
//        List<Favorite> favoritesOfUser = favoriteRepository.findAllByUserId(user.id());
        List<Long> itemIds = favoriteRepository.findItemIdsByUserId(user.id());
        return itemRepository.findAllById(itemIds);
//        return items;
//        return new Assembly(items);
    }

    // the incoming item is from request, not database
    @CacheEvict(cacheNames = "recommend_items", key = "#user")
    @Transactional
    public void addFavorite(UserEntity user, Item item) {
        Item existedItem = itemRepository.findByTwitchId(item.twitchId());
        // if this item is new to database
        if (existedItem == null) {
            existedItem = itemRepository.save(item);
        }
        if (favoriteRepository.existsByUserIdAndItemId(user.id(), existedItem.id())) {
            System.out.println("You already have a favorite");
            throw new RuntimeException("You already have a favorite");
        }
        favoriteRepository.save(new Favorite(null, user.id(), existedItem.id(), Instant.now()));
    }
    @CacheEvict(cacheNames = "recommend_items", key = "#user")
    public void deleteFavorite(UserEntity user, Item item) {
        Item existedItem = itemRepository.findByTwitchId(item.twitchId());
        if (existedItem == null) {
            System.out.println("Item does not exist");
            return;
        }
        favoriteRepository.deleteByUserIdAndItemId(user.id(), existedItem.id());
    }
}
