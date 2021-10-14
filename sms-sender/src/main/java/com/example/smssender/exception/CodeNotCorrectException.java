package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class CodeNotCorrectException extends BusinessException {
    public CodeNotCorrectException() {
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
