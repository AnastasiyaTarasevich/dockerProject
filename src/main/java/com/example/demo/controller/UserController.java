package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Optional;

@Controller
public class UserController {
    private final ResourceLoader resourceLoader;
    private final UserService userService;

    public UserController(@Qualifier("webApplicationContext") ResourceLoader resourceLoader, UserService userService) {
        this.resourceLoader = resourceLoader;
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "my";
    }


    @GetMapping("/profile-picture")
    public ResponseEntity<byte[]> getProfilePicture() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:images/profile-1.jpg");

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] imageBytes = resource.getInputStream().readAllBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(imageBytes);
    }


    @GetMapping("/get-profile")
    @ResponseBody
    public User getProfile() {
        Optional<User> user = userService.getUserByUserid(1);
        return user.orElse(new User());
    }
    @PostMapping("/update-profile")
    @ResponseBody
    public User updateProfile(@RequestBody User user) {
        user.setUserId(1);
        return userService.updateUser(user);
    }
}
