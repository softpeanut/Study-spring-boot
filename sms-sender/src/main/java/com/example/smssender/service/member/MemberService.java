package com.example.smssender.service.member;

import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;

public interface MemberService {
    void sendCode(String phoneNumber);
    void resendCode(String phoneNumber);
    void verifyAccount(SmsVerifiedRequest request);
    void signup(SignupRequest request);
}
