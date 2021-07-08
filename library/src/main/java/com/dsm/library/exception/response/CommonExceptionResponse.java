package com.dsm.library.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommonExceptionResponse {
    private final String code;
    private final String message;
}

