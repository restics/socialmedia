package com.restics.socialmedia.service;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.Post;
import com.restics.socialmedia.model.User;
import com.restics.socialmedia.repository.FollowRepository;
import com.restics.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;

    private final FollowRepository followRepo;

    public UserService(UserRepository userRepo, FollowRepository followRepo) {
        this.userRepo = userRepo;
        this.followRepo = followRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByName(String name){
        return userRepo.findByName(name);
    }

    public User getUserById(int id){
        return userRepo.findById(id);
    }
    
    public List<User> getFollowedUsers(int userId) {
        return followRepo.getFollowedUsers(userId);
    }

    public void follow(int followerId, int followedId){
        followRepo.followUser(followerId, followedId);
    }

    public void unfollow(int followerId, int followedId){
        followRepo.unfollowUser(followerId, followedId);
    }

    public boolean isFollowing(int followerId, int followedId) {
        return followRepo.FollowedByUserId(followerId, followedId);
    }

}
