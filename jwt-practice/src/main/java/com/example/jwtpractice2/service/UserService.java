package com.example.jwtpractice2.service;

import com.example.jwtpractice2.dto.UserResponse;
import com.example.jwtpractice2.entity.MemberRepository;
import com.example.jwtpractice2.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;

    @Transactional
    public UserResponse me() {
        System.out.println("me");
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(UserResponse::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));
    }
}
