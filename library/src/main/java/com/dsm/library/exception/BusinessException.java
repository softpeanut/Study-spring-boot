package com.dsm.library.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
    private final String code;
    private final String message;
    private final HttpStatus status;
}
