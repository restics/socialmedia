package com.restics.socialmedia.model;

import java.time.LocalDateTime;

public record User(
        int userId,
        String password,
        String email,
        String name,
        byte[] profilePicture,
        String bio,
        LocalDateTime createdAt
) {}
