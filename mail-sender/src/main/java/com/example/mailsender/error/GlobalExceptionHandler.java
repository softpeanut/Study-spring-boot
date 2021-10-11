package com.example.mailsender.error;

import com.example.mailsender.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorrResponse> handleException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorrResponse(errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorrResponse> handleValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new ErrorrResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

}
