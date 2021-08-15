package com.example.jwtpractice2.exception;

import com.example.jwtpractice2.error.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
