package com.restics.socialmedia.service;

import com.restics.socialmedia.model.User;
import com.restics.socialmedia.repository.UserRepository;
import com.restics.socialmedia.model.User;
import com.restics.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
