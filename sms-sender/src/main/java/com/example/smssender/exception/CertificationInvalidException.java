package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class CertificationInvalidException extends BusinessException {
    public CertificationInvalidException() {
        super(ErrorCode.CERTIFICATION_INVALID);
    }
}
