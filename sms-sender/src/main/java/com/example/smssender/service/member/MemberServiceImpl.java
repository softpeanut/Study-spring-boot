package com.example.smssender.service.member;

import com.example.smssender.entity.certification.Certification;
import com.example.smssender.entity.certification.CertificationRepository;
import com.example.smssender.entity.certification.Certified;
import com.example.smssender.entity.member.Member;
import com.example.smssender.entity.member.MemberRepository;
import com.example.smssender.payload.PhoneNumberRequest;
import com.example.smssender.payload.SignupRequest;
import com.example.smssender.payload.SmsVerifiedRequest;
import com.example.smssender.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final SmsService smsService;
    private final MemberRepository memberRepository;
    private final CertificationRepository certificationRepository;

    @Override
    public void sendCode(PhoneNumberRequest request) {
        smsService.sendCode(request.getPhoneNumber());
    }

    @Override
    @Transactional
    public void verifyAccount(SmsVerifiedRequest request) {
        Certification certification = certificationRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();

        System.out.println(certification.getCertified());

        if(request.getCode().equals(certification.getCode())) {
            certification.updateCertified(Certified.CERTIFIED);
        } else throw new IllegalArgumentException();

        System.out.println(certification.getCertified());

    }

    @Override
    @Transactional
    public void signup(SignupRequest request) {
        if(memberRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent())
            throw new IllegalArgumentException();
        else if(memberRepository.findByName(request.getName()).isPresent())
            throw new IllegalArgumentException();

        Certification certification = certificationRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();

        if(certification.getCertified() == Certified.CERTIFIED) {
            memberRepository.save(Member.builder()
                    .phoneNumber(request.getPhoneNumber())
                    .name(request.getName())
                    .password(request.getPassword())
                    .build());
        } else throw new IllegalArgumentException();

    }

}
