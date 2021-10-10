package com.example.mailsender.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerifiedRequest {
    private String email;
    private String code;
}
