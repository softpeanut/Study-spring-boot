package com.example.smssender.service.member;

import com.example.smssender.entity.certification.Certification;
import com.example.smssender.entity.certification.CertificationRepository;
import com.example.smssender.entity.certification.Certified;
import com.example.smssender.entity.member.Member;
import com.example.smssender.entity.member.MemberRepository;
import com.example.smssender.exception.*;
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
                .orElseThrow(PhoneNumberNotFoundException::new);

        if(request.getCode().equals(certification.getCode())) {
            certificationRepository.save(certification.updateCertified(Certified.CERTIFIED));
        } else throw new CodeNotCorrectException();

    }

    @Override
    @Transactional
    public void signup(SignupRequest request) {
        if(memberRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent())
            throw new MemberPhoneNumberAlreadyExistsException();
        else if(memberRepository.findByName(request.getName()).isPresent())
            throw new MemberNameAlreadyExistsException();
        else if(memberRepository.findByUsername(request.getUsername()).isPresent())
            throw new MemberUsernameAlreadyExistsException();

        Certification certification = certificationRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(CertificationNotFoundException::new);

        if(certification.getCertified() == Certified.CERTIFIED) {
            memberRepository.save(Member.builder()
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .build());
        } else throw new PhoneNumberInvalidException();

    }

}
