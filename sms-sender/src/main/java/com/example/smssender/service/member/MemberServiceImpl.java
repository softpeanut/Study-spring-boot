package com.example.smssender.service.member;

import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;
import com.example.smssender.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SmsService smsService;

    @Override
    public void sendCode(String phoneNumber) {
        smsService.sendMessage(phoneNumber);
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
