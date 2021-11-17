package com.example.fcmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String nickname;
    private String username;
    private String password;
}
