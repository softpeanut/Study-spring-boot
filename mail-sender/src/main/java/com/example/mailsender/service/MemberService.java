package com.example.mailsender.service;

import com.example.mailsender.payload.request.SignupRequest;

public interface MemberService {
    void verifyAccount(String token);
    void signup(SignupRequest request);
}
