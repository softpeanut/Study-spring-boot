package com.dms.project.exception;

import com.dms.project.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleBoardNotExistException(BusinessException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
