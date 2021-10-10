package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class EmailNotCertifiedException extends BusinessException {
    public EmailNotCertifiedException() {
        super(ErrorCode.EMAIL_NOT_CERTIFIED);
    }
}
