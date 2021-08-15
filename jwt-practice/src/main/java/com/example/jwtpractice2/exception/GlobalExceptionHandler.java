package com.example.jwtpractice2.exception;

import com.example.jwtpractice2.error.ErrorCode;
import com.example.jwtpractice2.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handleException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ExceptionResponse(errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus()));
    }
}
