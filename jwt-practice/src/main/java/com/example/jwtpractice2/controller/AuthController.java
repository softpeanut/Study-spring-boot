package com.example.jwtpractice2.controller;

import com.example.jwtpractice2.dto.MemberRequest;
import com.example.jwtpractice2.dto.TokenResponse;
import com.example.jwtpractice2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public String join(@RequestBody MemberRequest request) {
        return authService.join(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody MemberRequest request) {
        return authService.login(request);
    }

}
