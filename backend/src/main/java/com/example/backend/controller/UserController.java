package com.example.backend.controller;

import com.example.backend.dto.user.UserResponse;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserResponse getMe(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return userService.getLoginUser(userId);
    }
}
