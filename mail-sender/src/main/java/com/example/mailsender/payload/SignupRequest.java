package com.example.mailsender.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
