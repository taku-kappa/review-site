package com.example.backend.dto.review;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewListResponse {

    private List<ReviewResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
