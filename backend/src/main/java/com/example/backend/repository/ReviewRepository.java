package com.example.backend.repository;

import com.example.backend.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // 投稿日時が新しい順にページングして取得
    Page<ReviewEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

}