package com.restics.socialmedia.repository;

import com.restics.socialmedia.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcTemplate jdbc;
    
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    static final RowMapper<User> USER_MAPPER = (rs, rowNum) -> new User(
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
        return jdbc.queryForObject("SELECT * FROM users u WHERE u.user_id = ? ORDER BY user_id", USER_MAPPER, id);
    }

    public User findByName(String name) {
        return jdbc.queryForObject("SELECT * FROM users u WHERE u.name = ? ORDER BY user_id", USER_MAPPER, name);
    }

    public User findByEmail(String email){
        return jdbc.queryForObject("SELECT * FROM users u WHERE u.email = ? ORDER BY user_id", USER_MAPPER, email);
    }
    public int addUser(String name, String password, String email) {
        log.atInfo().log("Adding user %s", name);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO users (name, password, email) VALUES (?, ?, ?)",
                    new String[]{"user_id"}
            );
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteById(String id){
        jdbc.update("DELETE FROM users u WHERE u.user_id = ?", USER_MAPPER, id);
    }

    public void updateUser(User user) {
        log.atInfo().log("Updating user %s", user);
        jdbc.update("UPDATE users SET password = ?, email = ?, name = ?, profile_picture = ?, bio = ? WHERE user_id = ?",
                user.password(),
                user.email(),
                user.name(),
                user.profilePicture(),
                user.bio(),
                user.userId());
    }

    //todo: addfollow

}