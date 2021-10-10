package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class CodeNotCorrectException extends BusinessException {
    public CodeNotCorrectException() {
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
