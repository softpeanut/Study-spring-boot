package com.example.jwtsecurity.service;

import com.example.jwtsecurity.controller.dto.MemberRequestDto;
import com.example.jwtsecurity.controller.dto.MemberResponseDto;
import com.example.jwtsecurity.controller.dto.TokenResponseDto;
import com.example.jwtsecurity.controller.dto.TokenRequestDto;
import com.example.jwtsecurity.domain.member.Member;
import com.example.jwtsecurity.domain.refreshtoken.RefreshToken;
import com.example.jwtsecurity.domain.member.MemberRepository;
import com.example.jwtsecurity.domain.refreshtoken.RefreshTokenRepository;
import com.example.jwtsecurity.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public TokenResponseDto login(MemberRequestDto memberRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenResponseDto tokenResponseDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenResponseDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenResponseDto;
    }

    @Transactional
    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenResponseDto tokenResponseDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenResponseDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return tokenResponseDto;
    }

}
