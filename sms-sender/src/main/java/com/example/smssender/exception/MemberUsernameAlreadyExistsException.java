package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class MemberUsernameAlreadyExistsException extends BusinessException {
    public MemberUsernameAlreadyExistsException() {
        super(ErrorCode.MEMBER_USERNAME_ALREADY_EXISTS);
    }
}
