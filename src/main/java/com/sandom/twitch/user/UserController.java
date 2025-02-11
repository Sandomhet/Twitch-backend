package com.sandom.twitch.user;

import com.sandom.twitch.database.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserEntity user) {
        userService.register(user);
    }
    // This will be overriden by Spring Security
    @PostMapping("/login")
    public void login(@RequestBody UserEntity user) {
        userService.login(user.username(), user.password());
    }
}
