package com.example.jwtpractice2.service;

import com.example.jwtpractice2.dto.MemberRequest;
import com.example.jwtpractice2.dto.TokenResponse;
import com.example.jwtpractice2.entity.Member;
import com.example.jwtpractice2.entity.MemberRepository;
import com.example.jwtpractice2.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String join(MemberRequest request) {
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        memberRepository.save(
                Member.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());

        return "success join";
    }

    public TokenResponse login(MemberRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
