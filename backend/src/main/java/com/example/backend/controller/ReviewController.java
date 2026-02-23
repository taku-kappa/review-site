package com.example.backend.controller;

import com.example.backend.dto.review.ReviewCreateRequest;
import com.example.backend.dto.review.ReviewListResponse;
import com.example.backend.dto.review.ReviewUpdateRequest;
import com.example.backend.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 一覧取得
    @GetMapping
    public ReviewListResponse getReviews (HttpServletRequest request) {}

    // 作成
    public void createReview (
            HttpServletRequest request,
            @RequestBody ReviewCreateRequest dto
    ) {
        Long userId = (Long) request.getAttribute("userId");
        reviewService.createReview(userId, dto);
    }

    // 更新
    @PutMapping("/{id}")
    public void updateReview (
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody ReviewUpdateRequest dto
    ) {
        Long userId = (Long) request.getAttribute("userId");
        reviewService.updateReview(userId, id, dto);
    }

    // 削除
    @DeleteMapping("/{id}")
    public void deleteReview (
            HttpServletRequest request,
            @PathVariable Long id
    ) {
        Long userId = (Long) request.getAttribute("userId");
        reviewService.deleteReview(userId, id);
    }
}
