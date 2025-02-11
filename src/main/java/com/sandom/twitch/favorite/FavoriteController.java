package com.sandom.twitch.favorite;

import com.sandom.twitch.database.entity.Item;
import com.sandom.twitch.database.entity.UserEntity;
import com.sandom.twitch.database.Assembly;
import com.sandom.twitch.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final UserService userService;
    public FavoriteController(FavoriteService favoriteService, UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @GetMapping
    public Assembly getFavorite(@AuthenticationPrincipal User authUser) {
        UserEntity user = userService.findByUsername(authUser.getUsername());
        return new Assembly(favoriteService.getFavoriteItems(user));
    }
    @PostMapping
    public void addFavorite(@AuthenticationPrincipal User authUser, @RequestBody Item item) {
        UserEntity user = userService.findByUsername(authUser.getUsername());
        favoriteService.addFavorite(user, item);
    }
    @DeleteMapping
    public void deleteFavorite(@AuthenticationPrincipal User authUser, @RequestBody Item item) {
        UserEntity user = userService.findByUsername(authUser.getUsername());
        favoriteService.deleteFavorite(user, item);
    }
}
/*
get a user's favorite record
add a favorite to a user
delete a favorite to a user
 */