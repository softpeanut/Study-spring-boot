package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class PhoneNumberNotFoundException extends BusinessException {
    public PhoneNumberNotFoundException() {
        super(ErrorCode.PHONE_NUMBER_NOT_FOUND);
    }
}
