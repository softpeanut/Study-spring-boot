package com.example.mailsender.controller;

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
    public void sendEmail(@RequestBody String email) {
        memberService.sendEmail(email);
    }

    @PostMapping("/verify")
    public void verifyAccount(@RequestBody String code) {
        memberService.verifyAccount(code);
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
    }

}
