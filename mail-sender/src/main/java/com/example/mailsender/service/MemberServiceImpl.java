package com.example.mailsender.service;

import com.example.mailsender.entity.certification.Certification;
import com.example.mailsender.entity.certification.CertificationRepository;
import com.example.mailsender.entity.certification.Certified;
import com.example.mailsender.entity.member.Member;
import com.example.mailsender.entity.member.MemberRepository;
import com.example.mailsender.exception.EmailNotFoundException;
import com.example.mailsender.exception.MemberEmailAlreadyExistsException;
import com.example.mailsender.exception.MemberNameAlreadyExistsException;
import com.example.mailsender.payload.request.EmailRequest;
import com.example.mailsender.payload.request.EmailVerifiedRequest;
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
    private final CertificationRepository certificationRepository;
    private final MailService mailService;

    @Override
    public void sendEmail(EmailRequest request) {
        mailService.sendEmail(request.getEmail());
    }

    @Override
    @Transactional
    public void verifyAccount(EmailVerifiedRequest request) {
        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        if(request.getCode() == certification.getCode()) {
               certification.updateCertified(Certified.CERTIFIED);
        }
    }

    @Override
    @Transactional
    public void signup(SignupRequest request) {
        if(memberRepository.findByEmail(request.getEmail()).isPresent())
            throw new MemberEmailAlreadyExistsException();
        else if(memberRepository.findByName(request.getName()).isPresent())
            throw new MemberNameAlreadyExistsException();

        Certified certified = certificationRepository.findByEmail(request.getEmail())
                .map(certification -> certification.getCertified())
                .orElseThrow(EmailNotFoundException::new);

        if(certified == Certified.CERTIFIED) {
            memberRepository.save(Member.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build());
        }

    }

}
