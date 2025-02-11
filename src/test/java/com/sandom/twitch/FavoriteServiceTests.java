package com.sandom.twitch;

import com.sandom.twitch.database.ItemType;
import com.sandom.twitch.database.entity.Favorite;
import com.sandom.twitch.database.entity.Item;
import com.sandom.twitch.database.entity.UserEntity;
import com.sandom.twitch.database.repository.FavoriteRepository;
import com.sandom.twitch.database.repository.ItemRepository;
import com.sandom.twitch.favorite.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTests {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private FavoriteRepository favoriteRepository;

    private FavoriteService favoriteService;

    @BeforeEach
    public void setup() {
        favoriteService = new FavoriteService(favoriteRepository, itemRepository);
    }

    @Test
    public void whenItemNotExist_setFavoriteItem_shouldSaveItem() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        Item item = new Item(null, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Item persisted = new Item(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(null);
        Mockito.when(itemRepository.save(item)).thenReturn(persisted);

        favoriteService.addFavorite(user, item);

        Mockito.verify(itemRepository).save(item);
    }

    @Test
    public void whenItemExist_setFavoriteItem_shouldNotSaveItem() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        Item item = new Item(null, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Item persisted = new Item(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(persisted);

        favoriteService.addFavorite(user, item);

        Mockito.verify(itemRepository, Mockito.never()).save(item);
    }

    @Test
    public void whenItemNotExist_unsetFavoriteItem_shouldNotDeleteFavoriteRecord() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(null);
        Item item = itemRepository.findByTwitchId("twitchId");

        favoriteService.deleteFavorite(user, item);
        Mockito.verifyNoInteractions(favoriteRepository);
    }

    @Test
    public void whenItemExist_unsetFavoriteItem_shouldDeleteFavoriteRecord() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        Item persisted = new Item(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(persisted);
        Item item = itemRepository.findByTwitchId("twitchId");

        favoriteService.deleteFavorite(user, item);
//        Mockito.verify(favoriteRepository).delete(new Favorite());
    }
}
