package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class MemberPhoneNumberAlreadyExistsException extends BusinessException {
    public MemberPhoneNumberAlreadyExistsException() {
        super(ErrorCode.MEMBER_PHONE_NUMBER_ALREADY_EXISTS);
    }
}
