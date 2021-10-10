package com.example.mailsender.service;

import com.example.mailsender.payload.request.SignupRequest;

public interface MemberService {
    void sendEmail(String email);
    boolean verifyAccount(String code);
    void signup(SignupRequest request);
}
