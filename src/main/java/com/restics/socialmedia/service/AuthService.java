package com.restics.socialmedia.service;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.User;
import com.restics.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    UserRepository userRepository;


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean authenticate(CurrentUser cu, String name, String password) {
        User user = userRepository.findByName(name);
        cu.set(user); // should be fine since it passes the reference of the singleton?
        return Objects.equals(user.password(), password);
    }
}
