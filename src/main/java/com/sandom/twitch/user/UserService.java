package com.sandom.twitch.user;

import com.sandom.twitch.database.entity.UserEntity;
import com.sandom.twitch.database.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void register(UserEntity user) {
        UserDetails authUser = User.builder()
                .username(user.username())
                .password(passwordEncoder.encode(user.password()))
                .roles("USER")
                .build();
        userDetailsManager.createUser(authUser);
//        userRepository.updateNameByUsername()
    }
    public void login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        if (!user.password().equals(password)) {
            System.out.println("Wrong password");
        }
    }
}
