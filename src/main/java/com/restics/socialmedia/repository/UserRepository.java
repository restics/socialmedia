package com.restics.socialmedia.repository;

import com.restics.socialmedia.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;
    
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final RowMapper<User> USER_MAPPER = (rs, rowNum) -> new User(
            rs.getInt("user_id"),
            rs.getString("password"),
            rs.getString("email"),
            rs.getString("name"),
            rs.getBytes("profile_picture"),
            rs.getString("bio"),
            rs.getTimestamp("created_at").toLocalDateTime()
    );

    public List<User> findAll() {
        return jdbc.query("SELECT * FROM users ORDER BY name", USER_MAPPER);
    }
}