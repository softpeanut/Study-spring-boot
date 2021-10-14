package com.example.smssender.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    CODE_NOT_CORRECT(401, "Code Not Correct"),
    PHONE_NUMBER_INVALID(401, "Phone Number Invalid"),
    PHONE_NUMBER_NOT_FOUND(404, "Phone Number Not Found"),

    CERTIFICATION_INVALID(401, "Certification Invalid"),
    CERTIFICATION_NOT_FOUND(404, "Certification Not Found"),

    COOLSMS_CONNECT_FAILED(500, "Coolsms Connect Failed"),

    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_USERNAME_ALREADY_EXISTS(409, "Member Username Already Exists"),
    MEMBER_PHONE_NUMBER_ALREADY_EXISTS(409, "Member Phone Number Already Exists");


    private int status;
    private String message;
}
