package com.sandom.twitch.database.repository;

import com.sandom.twitch.database.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
