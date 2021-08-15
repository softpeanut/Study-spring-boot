package com.example.jwtpractice2.exception;

import com.example.jwtpractice2.error.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
