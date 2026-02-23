package com.example.backend.service;

import com.example.backend.dto.review.ReviewCreateRequest;
import com.example.backend.dto.review.ReviewListResponse;
import com.example.backend.dto.review.ReviewUpdateRequest;
import com.example.backend.entity.ReviewEntity;
import com.example.backend.repository.ReviewRatingRepository;
import com.example.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepositry;
    private final ReviewRatingRepository reviewRatingRepository;

    public ReviewService (
            ReviewRepository reviewRepository,
            ReviewRatingRepository reviewRatingRepository
    ) {
        this.reviewRepositry = reviewRepository;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    // Review一覧取得
    public ReviewListResponse getReviews () {}

    // Review作成
    public void createReview (Long userId, ReviewCreateRequest dto) {
        reviewRepositry.findById(dto.getTargetId())
                .orElseThrow(() -> new RuntimeException("Target not found"));

        ReviewEntity review = new ReviewEntity();
        review.setUserId(userId);
        review.setTitle(dto.getTitle());
        review.setContent(dto.getContent());
        review.setTargetType(dto.getTargetType());
        review.setTargetId(dto.getTargetId());

        reviewRepositry.save(review);
    }

    // Review更新
    public void updateReview (Long userId, Long id, ReviewUpdateRequest dto) {
        ReviewEntity review = reviewRepositry.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (review.getUserId() != userId) {
            throw new RuntimeException("Forbidden");
        }

        if (dto.getTitle() != null) {
            review.setTitle(dto.getTitle());
        }

        if (dto.getContent() != null) {
            review.setContent(dto.getContent());
        }

        reviewRepositry.save(review);
    }

    // Review削除
    public void deleteReview (Long userId, Long id) {
        ReviewEntity review = reviewRepositry.findbyId(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (review.getUserId() != userId) {
            throw new RuntimeException("Forbidden");
        }

        reviewRepositry.delete(review);
    }

    // Entity -> DTO
    private ReviewListResponse toResponseDto () {}

}
