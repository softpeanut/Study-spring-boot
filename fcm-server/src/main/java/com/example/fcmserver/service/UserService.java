package com.example.fcmserver.service;

import com.example.fcmserver.domain.entity.User;
import com.example.fcmserver.domain.repository.UserRepository;
import com.example.fcmserver.dto.AuthRequest;
import com.example.fcmserver.dto.TokenResponse;
import com.example.fcmserver.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String signup(AuthRequest request) {
        if(userRepository.findById(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("user already exists");
        }

        userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

        return "success";
    }

    @Transactional
    public TokenResponse login(AuthRequest request) {
        return userRepository.findById(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> new TokenResponse(jwtTokenProvider.generateAccessToken(request.getUsername())))
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

}
