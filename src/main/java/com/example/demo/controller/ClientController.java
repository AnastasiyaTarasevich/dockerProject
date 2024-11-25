package com.example.demo.controller;


import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@AllArgsConstructor

public class ClientController {
    private final ResourceLoader resourceLoader;
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
}
