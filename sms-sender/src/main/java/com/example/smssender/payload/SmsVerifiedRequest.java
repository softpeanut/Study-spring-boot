package com.example.smssender.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SmsVerifiedRequest {

    private String phoneNumber;

    private String code;
}
