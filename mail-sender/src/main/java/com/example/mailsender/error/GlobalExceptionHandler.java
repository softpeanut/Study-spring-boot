package com.example.mailsender.error;

import com.example.mailsender.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handle(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus()));
    }

}
