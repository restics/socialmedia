package com.restics.socialmedia.service;

import com.restics.socialmedia.dto.UserResponseDTO;
import com.restics.socialmedia.model.Post;
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
    
    public List<User> getFollowedUsers(String userId) { return null;}
    
    public List<Post> getPostsOfUser(String userId){ return null;}
}
