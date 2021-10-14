package com.example.smssender.controller;

import com.example.smssender.payload.PhoneNumberRequest;
import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;
import com.example.smssender.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/code")
    public void sendCode(@RequestBody PhoneNumberRequest request) {
        memberService.sendCode(request);
    }

    @GetMapping("/code/re")
    public void resendCode(@RequestBody PhoneNumberRequest request) {
        memberService.resendCode(request);
    }

    @PostMapping("/verify")
    public void verifyAccount(@RequestBody SmsVerifiedRequest request) {
        memberService.verifyAccount(request);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request) {
        memberService.signup(request);
    }

}
