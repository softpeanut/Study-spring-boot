package com.example.mailsender.service;

import com.example.mailsender.entity.member.Member;
import com.example.mailsender.entity.member.MemberRepository;
import com.example.mailsender.payload.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MailService mailService;

    @Override
    public void sendEmail(String email) {
        mailService.sendEmail(email);
    }

    @Override
    public boolean verifyAccount(String code) {
        return code.equals(MailService.createKey());
    }

    @Override
    @Transactional
    public void signup(SignupRequest request) {
        if(memberRepository.findByEmail(request.getEmail()).isPresent())
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        else if(memberRepository.findByName(request.getName()).isPresent())
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");

        memberRepository.save(Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
    }

}
