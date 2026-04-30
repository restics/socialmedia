package com.restics.socialmedia.service;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.Post;
import com.restics.socialmedia.repository.LikeRepository;
import com.restics.socialmedia.repository.PostRepository;
import com.restics.socialmedia.repository.ShareRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;
    LikeRepository likeRepository;
    ShareRepository shareRepository;
    CurrentUser currentUser;
    public PostService(PostRepository postRepository, LikeRepository likeRepository, ShareRepository shareRepository, CurrentUser currentUser) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.shareRepository = shareRepository;
        this.currentUser = currentUser;
    }
    public List<Post> getPostsOfUser(int userId){
        return postRepository.getPostsByAuthorId(userId);
    }

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }
    public List<Post> findFollowingPosts(){
        if (!currentUser.isLoggedIn()){
            return new ArrayList<>();
        }
        return postRepository.getFollowingPosts(currentUser.get().userId()); //TODO: switch with current user later
    }

    public void deletePost(int postId){
        postRepository.deletePost(postId);
    }

    public void updatePost(int postId, String newText){
        postRepository.updatePostContent(postId, "text", newText, "");
    }

    // postId represents the ID of the parent post, not the new one.
    // returns the ID of the newly created post.
    @Transactional
    public int replyToPost(int postId, String newText){
        if (!currentUser.isLoggedIn()){
            return -1;
        }
        int newpostid = postRepository.insertPost(currentUser.get().userId(), postId);
        postRepository.insertPostContent(newpostid,"text", newText, "");
        return newpostid;
    }

    public List<Post> findReplies(int postId){
        return postRepository.getPostReplies(postId);
    }


    public void likePost(int postId){
        if (!currentUser.isLoggedIn()){
            return;
        }
        likeRepository.likePost(currentUser.get().userId(), postId);
    }

    public void sharePost(int postId){
        if (!currentUser.isLoggedIn()){
            return;
        }
        shareRepository.sharePost(currentUser.get().userId(), postId);
    }


}
