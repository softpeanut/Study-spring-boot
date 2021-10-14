package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class PhoneNumberInvalidException extends BusinessException {
    public PhoneNumberInvalidException() {
        super(ErrorCode.PHONE_NUMBER_INVALID);
    }
}
