package com.restics.socialmedia.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Post(
        int postId,
        Integer parentPostId, // nullable
        String author,
        int authorId,
        String text,
        String imageUrl,
        int likes,
        int replies,
        int shares,
        LocalDateTime createdAt) {

    public String getFormattedTimestamp() { // readble date format
        return createdAt != null ? createdAt.format(DateTimeFormatter.ofPattern("MMM dd, yyyy · HH:mm")) : "";
    }}
