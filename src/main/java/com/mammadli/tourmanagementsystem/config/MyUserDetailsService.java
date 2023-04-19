package com.mammadli.tourmanagementsystem.config;

import com.mammadli.tourmanagementsystem.model.entity.User;
import com.mammadli.tourmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserName(username);
        user
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        return user.map(MyUserDetails::new).get();
    }
}
