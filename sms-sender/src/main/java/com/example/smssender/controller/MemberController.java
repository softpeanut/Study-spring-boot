package com.example.smssender.controller;

import com.example.smssender.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/code")
    public void sendCode(String phoneNumber) {
        memberService.sendCode(phoneNumber);
    }

    @GetMapping("/code/re")
    public void resendCode(String phoneNumber) {
        memberService.resendCode(phoneNumber);
    }

}
