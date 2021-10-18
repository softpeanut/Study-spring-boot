package com.example.mailsender.service.member;

import com.example.mailsender.entity.certification.Certification;
import com.example.mailsender.entity.certification.CertificationRepository;
import com.example.mailsender.entity.certification.Certified;
import com.example.mailsender.entity.member.Member;
import com.example.mailsender.entity.member.MemberRepository;
import com.example.mailsender.exception.*;
import com.example.mailsender.payload.EmailRequest;
import com.example.mailsender.payload.EmailVerifiedRequest;
import com.example.mailsender.payload.SignupRequest;
import com.example.mailsender.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final CertificationRepository certificationRepository;

    @Override
    public void sendEmail(EmailRequest request) {
        if(memberRepository.findByEmail(request.getEmail()).isPresent())
            throw new MemberEmailAlreadyExistsException();

        mailService.sendEmail(request.getEmail());
    }

    @Override
    @Transactional
    public void verifyAccount(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(s -> request.getCode().equals(s.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(CodeNotCorrectException::new);
    }

    @Override
    @Transactional
    public void signup(SignupRequest request) {
        if(memberRepository.findByName(request.getName()).isPresent())
            throw new MemberNameAlreadyExistsException();

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(CodeAlreadyExpiredException::new);

        if(certification.getCertified() == (Certified.CERTIFIED)) {
            memberRepository.save(Member.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build());
        } else throw new EmailNotCertifiedException();

    }

}
