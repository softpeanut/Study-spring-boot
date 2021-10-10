package com.example.mailsender.service;

import com.example.mailsender.payload.EmailRequest;
import com.example.mailsender.payload.EmailVerifiedRequest;
import com.example.mailsender.payload.SignupRequest;

public interface MemberService {
    void sendEmail(EmailRequest request);
    void verifyAccount(EmailVerifiedRequest request);
    void signup(SignupRequest request);
}
