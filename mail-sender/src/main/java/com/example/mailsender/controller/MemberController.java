package com.example.mailsender.controller;

import com.example.mailsender.payload.EmailRequest;
import com.example.mailsender.payload.EmailVerifiedRequest;
import com.example.mailsender.payload.SignupRequest;
import com.example.mailsender.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/email")
    public void sendEmail(@Valid @RequestBody EmailRequest request) {
        memberService.sendEmail(request);
    }

    @PostMapping("/verify")
    public void verifyAccount(@Valid @RequestBody EmailVerifiedRequest request) {
        memberService.verifyAccount(request);
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
    }

}
