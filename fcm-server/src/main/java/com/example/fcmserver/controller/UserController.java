package com.example.fcmserver.controller;

import com.example.fcmserver.dto.AuthRequest;
import com.example.fcmserver.dto.TokenResponse;
import com.example.fcmserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public String signup(@RequestBody AuthRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }

}
