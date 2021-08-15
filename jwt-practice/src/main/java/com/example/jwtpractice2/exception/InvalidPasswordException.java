package com.example.jwtpractice2.exception;

import com.example.jwtpractice2.error.ErrorCode;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
