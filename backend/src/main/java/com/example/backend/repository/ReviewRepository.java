package com.example.backend.repository;

import com.example.backend.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<ReviewEntity, Long> {

    // ユーザーごとのレビュー取得
    List<ReviewEntity> findByUserId(Long userId);

    // 特定ターゲットへのレビュー一覧
    List<ReviewEntity> findByTargetTypeAndTargetId(
            String targetType,
            Long targetId
    );
}