package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class EmailNotFoundException extends BusinessException {
    public EmailNotFoundException() {
        super(ErrorCode.EMAIL_NOT_FOUND);
    }
}
