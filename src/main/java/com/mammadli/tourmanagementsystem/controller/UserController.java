package com.mammadli.tourmanagementsystem.controller;

import com.mammadli.tourmanagementsystem.dto.UserDto;
import com.mammadli.tourmanagementsystem.jwt.JwtService;
import com.mammadli.tourmanagementsystem.model.entity.User;
import com.mammadli.tourmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@CrossOrigin(origins = "http://localhost:8080/") //@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
        log.info("authentication.isAuthenticated()  {} ", authentication);

        if (authentication.isAuthenticated()) {
//            log.info("jwtService.generateToken(authRequest.getName())  {} ", jwtService.generateToken(userDto.getUserName()).toString());
            return jwtService.generateToken(userDto.getUserName());
        } else {
            throw new UsernameNotFoundException("The user cannot be authenticated");
        }
    }

    @PostMapping("/signup")
    public User signupUser(@RequestBody User user){
        return userService.addUser(user);
    }


    @GetMapping("/public")
    public String publicAPI() {
        return "This is an unprotected endpoint";
    }

    @GetMapping("/test")
    public String adminTestAPI() {
        return "This is a test endpoint";
    }
    @GetMapping("/admin")
    public String adminAPI() {
        return "Protected endpoint - only admins are allowed";
    }

    @GetMapping("/user")
    public String userAPI() {
        return "Protected endpoint - only users are allowed";
    }
}
