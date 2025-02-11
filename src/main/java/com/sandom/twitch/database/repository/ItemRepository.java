package com.sandom.twitch.database.repository;

import com.sandom.twitch.database.entity.Item;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ListCrudRepository<Item, Long> {
//    List<Item> findAllById(List<Long> ids);
//    List<Item> findAllByTwitchId(List<Long> ids);
//    boolean existsByItemId(Long itemId);
    Item findByTwitchId(String twitchId);
}
