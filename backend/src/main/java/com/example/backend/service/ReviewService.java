package com.example.backend.service;

import com.example.backend.dto.rating.RatingRequest;
import com.example.backend.dto.rating.RatingResponse;
import com.example.backend.dto.review.ReviewCreateRequest;
import com.example.backend.dto.review.ReviewListResponse;
import com.example.backend.dto.review.ReviewUpdateRequest;
import com.example.backend.entity.ReviewEntity;
import com.example.backend.entity.ReviewRatingEntity;
import com.example.backend.repository.ReviewRatingRepository;
import com.example.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewRatingRepository reviewRatingRepository;

    public ReviewService (
            ReviewRepository reviewRepository,
            ReviewRatingRepository reviewRatingRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.reviewRatingRepository = reviewRatingRepository;
    }

    // Review一覧取得
    public ReviewListResponse getReviews () {}

    // Review作成
    public void createReview (Long userId, ReviewCreateRequest dto) {
        reviewRepository.findById(dto.getTargetId())
                .orElseThrow(() -> new RuntimeException("Target not found"));

        ReviewEntity review = new ReviewEntity();
        review.setUserId(userId);
        review.setTitle(dto.getTitle());
        review.setContent(dto.getContent());
        review.setTargetType(dto.getTargetType());
        review.setTargetId(dto.getTargetId());

        reviewRepository.save(review);
    }

    // Review更新
    public void updateReview (Long userId, Long id, ReviewUpdateRequest dto) {
        ReviewEntity review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }

        if (dto.getTitle() != null) {
            review.setTitle(dto.getTitle());
        }

        if (dto.getContent() != null) {
            review.setContent(dto.getContent());
        }

        reviewRepository.save(review);
    }

    // Review削除
    public void deleteReview (Long userId, Long id) {
        ReviewEntity review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (review.getUserId() != userId) {
            throw new RuntimeException("Forbidden");
        }

        reviewRepository.delete(review);
    }

    // 評価追加 or 更新
    @Transactional
    public RatingResponse rateReview(
            Long userId,
            Long reviewId,
            RatingRequest dto
    ) {

        // レビュー存在確認
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // rating値チェック
        if (dto.getRating() == null ||
                dto.getRating() < 1 ||
                dto.getRating() > 5) {
            throw new RuntimeException("Invalid rating value");
        }

        // 既存評価チェック
        ReviewRatingEntity rating = reviewRatingRepository
                .findByReviewIdAndUserId(reviewId, userId)
                .orElse(null);

        if (rating == null) {
            // 新規作成
            rating = ReviewRatingEntity.builder()
                    .reviewId(reviewId)
                    .userId(userId)
                    .rating(dto.getRating())
                    .build();
        } else {
            // 更新
            rating.setRating(dto.getRating());
        }

        reviewRatingRepository.save(rating);

        return RatingResponse.builder()
                .reviewId(reviewId)
                .myRating(rating.getRating())
                .build();
    }
}

    // Entity -> DTO
    // private ReviewListResponse toResponseDto () {}

}
