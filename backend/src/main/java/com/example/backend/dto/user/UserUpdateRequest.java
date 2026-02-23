package com.example.backend.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    private String username;
    private String bio;
    private String profileImageUrl;
}