package com.restics.socialmedia.service;

import com.restics.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean register(String name, String password, String email) {
        userRepository.addUser(name, password, email);
        return true; // this should be used for error handling but we assume no errors right now
    }
}
