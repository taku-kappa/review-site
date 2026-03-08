package com.example.backend.repository;

import com.example.backend.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
    // フォロー状態の確認用
    Optional<FollowEntity> findByFollowerUserIdAndFollowedUserId(Long followerUserId, Long followedUserId);

    // フォロー数のカウント
    Long countByFollowerUserId(Long followerUserId); // フォロー中
    Long countByFollowedUserId(Long followedUserId); // フォロワー

    // フォロー済みチェック
    boolean existsByFollowerUserIdAndFollowedUserId(Long followerUserId, Long followedUserId);
}