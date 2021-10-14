package com.example.smssender.service.member;

import com.example.smssender.entity.certification.CertificationRepository;
import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;
import com.example.smssender.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final SmsService smsService;
    private final CertificationRepository certificationRepository;

    @Override
    public void sendCode(String phoneNumber) {
        smsService.sendCode(phoneNumber);
    }

    @Override
    public void resendCode(String phoneNumber) {
        smsService.resendCode(phoneNumber);
    }

    @Override
    public void verifyAccount(SmsVerifiedRequest request) {

    }

    @Override
    public void signup(SignupRequest request) {

    }
}
