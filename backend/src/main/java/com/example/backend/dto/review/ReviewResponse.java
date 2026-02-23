package com.example.backend.dto.review;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponse {

    private Long id;
    private String title;
    private String content;

    private Long authorId;
    private String authorName;

    private Double averageRating;
    private Long ratingsCount;

    private LocalDateTime createdAt;
}