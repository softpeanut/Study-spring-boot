package com.example.smssender.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhoneNumberRequest {
    private String phoneNumber;
}
