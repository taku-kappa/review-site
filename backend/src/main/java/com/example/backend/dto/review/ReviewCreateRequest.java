package com.example.backend.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCreateRequest {

    private String title;
    private String content;
    private String targetType;
    private Long targetId;
}
