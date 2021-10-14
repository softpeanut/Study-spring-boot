package com.example.smssender.service.member;

import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public void sendCode(String phoneNumber) {

    }

    @Override
    public void resendCode(String phoneNumber) {

    }

    @Override
    public void verifyAccount(SmsVerifiedRequest request) {

    }

    @Override
    public void signup(SignupRequest request) {

    }
}
