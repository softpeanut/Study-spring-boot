package com.dsm.library.exception;

import org.springframework.http.HttpStatus;

public class LibraryAlreadyExistsException extends BusinessException {
    public LibraryAlreadyExistsException(String name) {
        super("LIBRARY_ALREADY_EXISTS", "LibraryName이 "+name+"인 도서관이 이미 존재합니다.", HttpStatus.CONFLICT);
    }
}
