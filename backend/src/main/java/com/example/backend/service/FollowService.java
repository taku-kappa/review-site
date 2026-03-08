package com.example.backend.service;

import com.example.backend.entity.FollowEntity;
import com.example.backend.repository.FollowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Transactional
    public void follow(Long followerId, Long followedId) {
        if (followerId.equals(followedId)) throw new RuntimeException("Self-follow not allowed");
        if (followRepository.existsByFollowerUserIdAndFollowedUserId(followerId, followedId)) return;

        FollowEntity follow = FollowEntity.builder()
                .followerUserId(followerId)
                .followedUserId(followedId)
                .build();
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long followerId, Long followedId) {
        followRepository.findByFollowerUserIdAndFollowedUserId(followerId, followedId)
                .ifPresent(follow -> followRepository.delete(follow));
    }
}
