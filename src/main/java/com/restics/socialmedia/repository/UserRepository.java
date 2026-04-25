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
    
    public User findById(int id) {
        return jdbc.queryForObject(
            "SELECT * FROM users WHERE user_id = ?",
            USER_MAPPER,
            id
        );
    }

    public void save(User user) {
        jdbc.update(
            "INSERT INTO users (user_id, password, email, name, bio) VALUES (?, ?, ?, ?, ?)",
            user.userId(),
	    user.password(),
	    user.email(),
	    user.name(),
	    user.bio()
        );
    }

    public void deleteById(int id) {
        jdbc.update(
            "DELETE FROM users WHERE user_id = ?",
            id
        );
    }
}
