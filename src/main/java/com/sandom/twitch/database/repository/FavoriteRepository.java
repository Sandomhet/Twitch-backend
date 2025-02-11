package com.sandom.twitch.database.repository;

import com.sandom.twitch.database.entity.Favorite;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends ListCrudRepository<Favorite, Long> {
    List<Favorite> findAllByUserId(Long userId);

    @Query("SELECT item_id FROM favorite_records WHERE user_id = :userId")
    List<Long> findItemIdsByUserId(Long userId);
    boolean existsByUserIdAndItemId(Long userId, Long itemId);
//    void save(FavoriteRecord favoriteRecord);
    @Modifying
    @Query("DELETE FROM favorite_records WHERE user_id = :userId AND item_id = :itemId")
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}
