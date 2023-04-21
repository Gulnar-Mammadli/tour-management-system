package com.mammadli.tourmanagementsystem.service;

import com.mammadli.tourmanagementsystem.model.entity.User;
import com.mammadli.tourmanagementsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {
        userRepository.findByUserName(user.getUserName()).ifPresent(u -> {
            throw new RuntimeException(String.format("User %s already exists", user.getUserName()));
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("A new user  {} is added", user);
        return user;
    }
}
