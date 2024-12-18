package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public Optional<User> getUserByUserid(int userid) {
        return Optional.ofNullable(userRepo.findByUserId(userid));
    }

    public User updateUser(User user) {
        User user1 = userRepo.findByUserId(user.getUserId());
        if (user1 != null) {
            user.setId(user1.getId());
        }
        return userRepo.save(user);
    }

}
