package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class MemberNameAlreadyExistsException extends BusinessException {
    public MemberNameAlreadyExistsException() {
        super(ErrorCode.MEMBER_NAME_ALREADY_EXISTS);
    }
}
