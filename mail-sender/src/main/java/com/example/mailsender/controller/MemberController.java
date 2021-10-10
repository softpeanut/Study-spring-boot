package com.example.mailsender.controller;

import com.example.mailsender.payload.request.EmailRequest;
import com.example.mailsender.payload.request.EmailVerifiedRequest;
import com.example.mailsender.payload.request.SignupRequest;
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
    public void sendEmail(@RequestBody EmailRequest request) {
        memberService.sendEmail(request);
    }

    @PostMapping("/verify")
    public void verifyAccount(@RequestBody EmailVerifiedRequest request) {
        memberService.verifyAccount(request);
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
    }

}
