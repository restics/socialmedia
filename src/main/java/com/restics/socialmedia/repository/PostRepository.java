package com.restics.socialmedia.repository;

import com.restics.socialmedia.model.Post;
import com.restics.socialmedia.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PostRepository.class);
    private final JdbcTemplate jdbc;

    public PostRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    private static final RowMapper<Post> POST_MAPPER = (rs, rowNum) -> new Post(
            rs.getInt("post_id"),
            rs.getObject("parent_post_id", Integer.class),
            rs.getString("name"),
            rs.getInt("user_id"),
            rs.getString("text_content"),
            rs.getString("media_url"),
            rs.getInt("numoflikes"),
            rs.getInt("numofreplies"),
            rs.getInt("numofshares"),
            rs.getTimestamp("created_at").toLocalDateTime()
    );
    public List<Post> findAll() {
        return jdbc.query("SELECT p.post_id, p.parent_post_id, u.name, u.user_id, p.numoflikes, p.numOfReplies, p.numofshares, p.created_at, pc.text_content, pc.media_url  FROM Post p JOIN Users u ON u.user_id = p.user_id JOIN PostContent pc ON pc.post_id = p.post_id",
                POST_MAPPER);
    }

    public List<Post> getPostsByAuthorName(String name){
        return jdbc.query(
                "SELECT p.post_id, p.parent_post_id, u.name, u.user_id, p.numoflikes, p.numOfReplies, p.numofshares, p.created_at, pc.text_content, pc.media_url  FROM Post p JOIN Users u ON u.user_id = p.user_id JOIN PostContent pc ON pc.post_id = p.post_id" +
                " WHERE p.name = ? ORDER BY p.created_at DESC",
                POST_MAPPER,
                name);
    }
    public List<Post> getPostsByAuthorId(int authorID){
        return jdbc.query(
                "SELECT p.post_id, p.parent_post_id, u.name, u.user_id, p.numoflikes, p.numOfReplies, p.numofshares, p.created_at, pc.text_content, pc.media_url  FROM Post p JOIN Users u ON u.user_id = p.user_id JOIN PostContent pc ON pc.post_id = p.post_id" +
                        " WHERE u.user_id = ? ORDER BY p.created_at DESC",
                POST_MAPPER,
                authorID);
    }
    public Optional<Post> getPostByID(int postID){
        return Optional.ofNullable(jdbc.queryForObject(
                "SELECT p.post_id, p.parent_post_id, u.name, u.user_id, p.numoflikes, p.numOfReplies, p.numofshares, p.created_at, pc.text_content, pc.media_url  FROM Post p JOIN Users u ON u.user_id = p.user_id JOIN PostContent pc ON pc.post_id = p.post_id" +
                        " WHERE p.post_id = ?",
                POST_MAPPER,
                postID));
    }

    public int insertPost(int authorId, Integer parentPostId){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO post (parent_post_id, user_id) VALUES (?, ?)",
                    new String[]{"post_id"}
            );
            ps.setObject(1, parentPostId == null ? -1 : parentPostId);
            ps.setInt(2, authorId);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int insertPostContent(int postId, String contenttype, String content, String mediaUrl){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO postcontent (post_id, content_type, text_content, media_url) VALUES (?, ?, ?, ?)",
                    new String[]{"post_id"}
            );
            ps.setInt(1, postId);
            ps.setString(2, contenttype);
            ps.setString(3, content);
            ps.setString(4, mediaUrl);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public List<Post> getPostReplies(int postId){
        return jdbc.query(
                "SELECT p.post_id, p.parent_post_id, u.name, u.user_id, p.numoflikes, p.numOfReplies, p.numofshares, p.created_at, pc.text_content, pc.media_url  FROM Post p JOIN Users u ON u.user_id = p.user_id JOIN PostContent pc ON pc.post_id = p.post_id" +
                        " WHERE p.parent_post_id = ? ORDER BY p.created_at DESC",
                POST_MAPPER,
                postId);
    }

    public int updatePostContent(int postId, String contenttype, String content, String mediaUrl){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE postcontent SET content_type = ?, text_content = ?, media_url = ? WHERE post_id = ?",
                    new String[]{"post_id"}
            );
            ps.setString(1, contenttype);
            ps.setString(2, content);
            ps.setString(3, mediaUrl);
            ps.setInt(4, postId);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int deletePost(int postId) {
        return jdbc.update("DELETE FROM post WHERE post_id = ?", postId);
    }



}
