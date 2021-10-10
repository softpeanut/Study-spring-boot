package com.example.mailsender.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_EMAIL_ALREADY_EXISTS(409, "Member Email Already Exists"),

    EMAIL_NOT_CERTIFIED(401, "Email Not Certified"),
    EMAIL_NOT_FOUND(404, "Email Not Found"),

    CODE_NOT_CORRECT(401, "Code Not Correct");

    private int status;
    private String message;
}
