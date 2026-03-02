package com.example.backend.repository;

import com.example.backend.entity.ReviewRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRatingRepository
        extends JpaRepository<ReviewRatingEntity, Long> {

    Optional<ReviewRatingEntity> findByReviewIdAndUserId(
            Long reviewId,
            Long userId
    );

    Long countByReviewId(Long reviewId);
}
