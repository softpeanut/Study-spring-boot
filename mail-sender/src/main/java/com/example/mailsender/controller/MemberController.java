package com.example.mailsender.controller;

import com.example.mailsender.payload.request.LoginRequest;
import com.example.mailsender.payload.request.SignupRequest;
import com.example.mailsender.payload.response.TokenResponse;
import com.example.mailsender.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/auth")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/verify")
    public void verifyAccount(@RequestBody String token) {
        memberService.verifyAccount(token);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request) {
        memberService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return memberService.login(request);
    }

}
