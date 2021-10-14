package com.example.smssender.service.member;

import com.example.smssender.payload.PhoneNumberRequest;
import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;

public interface MemberService {
    void sendCode(PhoneNumberRequest request);
    void resendCode(PhoneNumberRequest request);
    void verifyAccount(SmsVerifiedRequest request);
    void signup(SignupRequest request);
}
