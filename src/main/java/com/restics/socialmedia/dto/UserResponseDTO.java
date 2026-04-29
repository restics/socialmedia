package com.restics.socialmedia.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        int userId,
        String email,
        String name,
        byte[] profilePicture,
        String bio,
        LocalDateTime createdAt
) {}
