package com.example.backend.controller;

import com.example.backend.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class FollowController {
    private final FollowService followService;
    public FollowController(FollowService followService) { this.followService = followService; }

    @PostMapping("/{userId}/follow")
    public void follow(HttpServletRequest request, @PathVariable Long userId) {
        Long myId = (Long) request.getAttribute("userId");
        followService.follow(myId, userId);
    }

    @DeleteMapping("/{userId}/unfollow")
    public void unfollow(HttpServletRequest request, @PathVariable Long userId) {
        Long myId = (Long) request.getAttribute("userId");
        followService.unfollow(myId, userId);
    }
}
