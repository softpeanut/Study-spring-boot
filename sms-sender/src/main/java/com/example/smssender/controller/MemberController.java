package com.example.smssender.controller;

import com.example.smssender.payload.PhoneNumberRequest;
import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;
import com.example.smssender.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/code")
    public void sendCode(@Valid @RequestBody PhoneNumberRequest request) {
        memberService.sendCode(request);
    }

    @PostMapping("/verify")
    public void verifyAccount(@Valid @RequestBody SmsVerifiedRequest request) {
        memberService.verifyAccount(request);
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
    }

}
