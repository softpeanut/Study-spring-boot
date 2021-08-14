package com.example.jwtpractice2.controller;

import com.example.jwtpractice2.dto.UserResponse;
import com.example.jwtpractice2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse me() {
        return userService.me();
    }

}
