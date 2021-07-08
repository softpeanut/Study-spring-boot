package com.dsm.library.controller;

import com.dsm.library.exception.BusinessException;
import com.dsm.library.exception.response.CommonExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

//    @ExceptionHandler(Exception.class)
//    public Map<String, String> handleException(Exception e) {
//        Map<String, String> map = new HashMap<>();
//        map.put("Error!", e.getMessage());
//        return map;
//    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonExceptionResponse> handleLibraryNotFoundException(BusinessException e) {
        return new ResponseEntity<>(new CommonExceptionResponse(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
