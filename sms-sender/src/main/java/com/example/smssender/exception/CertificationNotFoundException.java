package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class CertificationNotFoundException extends BusinessException {
    public CertificationNotFoundException() {
        super(ErrorCode.CERTIFICATION_NOT_FOUND);
    }
}
