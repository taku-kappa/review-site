package com.example.backend.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String bio;
    private String profileImageUrl;
    private Long followersCount;
    private Long followingCount;
}
