package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
