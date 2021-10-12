package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class CodeAlreadyExpiredException extends BusinessException {
    public CodeAlreadyExpiredException() {
        super(ErrorCode.CODE_ALREADY_EXPIRED);
    }
}
