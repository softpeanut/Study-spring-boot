package com.example.mailsender.service;

import com.example.mailsender.payload.request.EmailRequest;
import com.example.mailsender.payload.request.EmailVerifiedRequest;
import com.example.mailsender.payload.request.SignupRequest;

public interface MemberService {
    void sendEmail(EmailRequest request);
    void verifyAccount(EmailVerifiedRequest request);
    void signup(SignupRequest request);
}
