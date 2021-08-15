package com.example.jwtpractice2.service;

import com.example.jwtpractice2.dto.MemberRequest;
import com.example.jwtpractice2.dto.TokenResponse;
import com.example.jwtpractice2.entity.Member;
import com.example.jwtpractice2.entity.MemberRepository;
import com.example.jwtpractice2.exception.InvalidPasswordException;
import com.example.jwtpractice2.exception.UserAlreadyExistsException;
import com.example.jwtpractice2.exception.UserNotFoundException;
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
            throw new UserAlreadyExistsException();
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
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException();
        }

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
