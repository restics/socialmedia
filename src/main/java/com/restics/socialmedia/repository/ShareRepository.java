package com.restics.socialmedia.repository;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShareRepository {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ShareRepository.class);
    private final JdbcTemplate jdbc;

    public ShareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public boolean sharedByUserId(int userId, int postId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM postshare WHERE user_id = ? AND post_id = ?",
                Integer.class,
                userId, postId
        );
        return count != null && count > 0;
    }

    public int sharePost(int userId, int postId) {
        return jdbc.update(
                "INSERT INTO postshare (user_id, post_id) VALUES (?, ?) ON CONFLICT DO NOTHING",
                userId, postId
        );
    }

    public int unsharePost(int userId, int postId) {
        return jdbc.update(
                "DELETE FROM postshare WHERE user_id = ? AND post_id = ?",
                userId, postId
        );
    }

    public int getPostShares(int postId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM postshare WHERE post_id = ?",
                Integer.class,
                postId
        );
        return count != null ? count : 0;
    }


}
