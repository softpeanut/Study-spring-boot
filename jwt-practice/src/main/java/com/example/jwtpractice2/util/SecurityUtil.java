package com.example.jwtpractice2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {
    private SecurityUtil() {}

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication==null || authentication.getName()==null) {
            throw  new RuntimeException("인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}
