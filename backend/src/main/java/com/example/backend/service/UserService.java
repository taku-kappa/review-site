package com.example.backend.service;

import com.example.backend.dto.user.UserResponse;
import com.example.backend.entity.UserEntity;
import com.example.backend.repository.FollowRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public UserService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    // ログインユーザー取得
    public UserResponse getLoginUser(Long userId) {
        if(userId == null) {
            throw new RuntimeException("Unauthorized");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .bio(user.getBio())
                .profileImageUrl(user.getProfileImageUrl())
                .followersCount(followRepository.countByFollowedUserId(userId)) //後に実装
                .followingCount(followRepository.countByFollowerUserId(userId))  //後に実装
                .build();
    }

}
