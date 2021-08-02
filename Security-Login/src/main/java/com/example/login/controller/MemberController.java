package com.example.login.controller;

import com.example.login.controller.dto.MemberRequestDto;
import com.example.login.controller.dto.MemberResponseDto;
import com.example.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "Welcome!";
    }

    @PostMapping("/api/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberDto) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @PostMapping("/login")
    public void login() {

    }

/*    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }*/

}
