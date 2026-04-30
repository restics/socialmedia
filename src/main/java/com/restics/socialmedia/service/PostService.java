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
    public PostService(PostRepository postRepository, LikeRepository likeRepository, ShareRepository shareRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.shareRepository = shareRepository;
    }
    public List<Post> getPostsOfUser(int userId){
        return postRepository.getPostsByAuthorId(userId);
    }

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }
    public List<Post> findFollowingPosts(int userId){
        return postRepository.getFollowingPosts(userId); //TODO: switch with current user later
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
    public int replyToPost(int userid, int postId, String newText){
        int newpostid = postRepository.insertPost(userid, postId);
        postRepository.insertPostContent(newpostid,"text", newText, "");
        return newpostid;
    }

    public List<Post> findReplies(int postId){
        return postRepository.getPostReplies(postId);
    }


    public void likePost(int userId, int postId){
        likeRepository.likePost(userId, postId);
    }

    public void sharePost(int userId, int postId){
        shareRepository.sharePost(userId, postId);
    }


}
