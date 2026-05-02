;--DROP TABLE IF EXISTS PostContent, PostShare, PostLike, Post, Follows, Users CASCADE;
CREATE TABLE IF NOT EXISTS Users (
                       user_id SERIAL PRIMARY KEY,
                       password    VARCHAR(32),
                       email   VARCHAR(32) UNIQUE,
                       name    VARCHAR(16),
                       profile_picture BYTEA,
                       bio VARCHAR(128),
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);
-- Add settings and following list attributes?

CREATE TABLE IF NOT EXISTS Post (
                      post_id SERIAL UNIQUE,
                      user_id INT REFERENCES Users(user_id),
                      parent_post_id INT NULL, -- NULL = parent post, else reply
                      numOfReplies    INT DEFAULT 0,
                      numOfLikes  INT DEFAULT 0,
                      numOfShares INT DEFAULT 0,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (post_id, user_id)
);

CREATE TABLE IF NOT EXISTS PostLike (
                          post_id INT REFERENCES Post(post_id) ON DELETE CASCADE,
                          user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY(post_id, user_id)
);

CREATE TABLE IF NOT EXISTS PostShare (
                                         post_id INT REFERENCES Post(post_id) ON DELETE CASCADE,
                                         user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         PRIMARY KEY(post_id, user_id)
);

CREATE TABLE IF NOT EXISTS PostContent (
                             content_id SERIAL PRIMARY KEY,
                             post_id INT,
                             content_type VARCHAR(20),   -- 'text', 'image', 'video', etc.
                             text_content TEXT,
                             media_url VARCHAR(500),
                             FOREIGN KEY (post_id) REFERENCES Post(post_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Follows (
                         follower_key INT REFERENCES Users(user_id) ON DELETE CASCADE,
                         followed_key INT REFERENCES Users(user_id) ON DELETE CASCADE,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (follower_key, followed_key),
                         CHECK (follower_key != followed_key)
)