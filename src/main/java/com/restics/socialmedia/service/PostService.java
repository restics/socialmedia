package com.restics.socialmedia.service;

import com.restics.socialmedia.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    public List<Post> getPostsOfUser(int userId){ return null;}

    public List<Post> findAllPosts(){ return null;}
    public List<Post> findFollowingPosts(){ return null;}

    public void deletePost(int postId){

    }

    public void updatePost(int postId, String newText){

    }

    public void replyToPost(int postId, String newText){}

    public List<Post> findReplies(int postId){return null;}


    public void likePost(int postId){}


}
