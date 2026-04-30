package com.restics.socialmedia.repository;

import com.restics.socialmedia.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class FollowRepository {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FollowRepository.class);;
    private final JdbcTemplate jdbc;

    public FollowRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean FollowedByUserId(int followerId, int followedId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM follows WHERE follower_key = ? AND followed_key = ?",
                Integer.class,
                followerId, followedId
        );
        return count != null && count > 0;
    }

    public int followUser(int followerId, int followedId) {
        return jdbc.update(
                "INSERT INTO follows (follower_key, followed_key) VALUES (?, ?) ON CONFLICT DO NOTHING",
                followerId, followedId
        );
    }

    public int unfollowUser(int followerId, int followedId) {
        return jdbc.update(
                "DELETE FROM follows WHERE follower_key = ? AND followed_key = ?",
                followerId, followedId
        );
    }

    public List<User> getFollowedUsers(int userId) {
        return jdbc.query(
                "SELECT u.* FROM users u JOIN follows f ON u.user_id = f.followed_key WHERE f.follower_key = ?",
                UserRepository.USER_MAPPER,
                userId
        );
    }

    public List<User> getFollowers(int userId) {
        return jdbc.query(
                "SELECT u.* FROM users u JOIN follows f ON u.user_id = f.follower_key WHERE f.followed_key = ?",
                UserRepository.USER_MAPPER,
                userId
        );
    }
}
