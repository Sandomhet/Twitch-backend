package com.sandom.twitch.recommendation;

import com.sandom.twitch.database.Assembly;
import com.sandom.twitch.database.entity.UserEntity;
import com.sandom.twitch.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {
    private final RecommendationService recommendationService;
    private final UserService userService;
    public RecommendationController(RecommendationService recommendationService, UserService userService) {
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    @GetMapping("/recommend")
    public Assembly getRecommendation(@AuthenticationPrincipal User authUser) {
        UserEntity user = null;
        if (authUser != null) {
            user = userService.findByUsername(authUser.getUsername());
        }
        return recommendationService.getRecommendation(user);
    }
}
