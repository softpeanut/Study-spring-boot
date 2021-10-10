package com.example.mailsender.service;

import com.example.mailsender.payload.request.LoginRequest;
import com.example.mailsender.payload.request.SignupRequest;
import com.example.mailsender.payload.response.TokenResponse;

public interface MemberService {
    void verifyAccount(String token);
    void signup(SignupRequest request);
    TokenResponse login(LoginRequest request);
}
