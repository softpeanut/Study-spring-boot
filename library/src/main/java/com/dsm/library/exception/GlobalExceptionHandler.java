package com.dsm.library.exception;

import com.dsm.library.exception.response.CommonExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonExceptionResponse> handleLibraryNotFoundException(BusinessException e) {
        return new ResponseEntity<>(new CommonExceptionResponse(e.getCode(), e.getMessage()), e.getStatus());
    }
}
