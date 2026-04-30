package com.restics.socialmedia.service;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.User;
import com.restics.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    UserRepository userRepository;

    CurrentUser currentUser;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        currentUser.set(user);
        return Objects.equals(user.password(), password);
    }
}
