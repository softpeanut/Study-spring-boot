package com.dsm.library.exception;

import org.springframework.http.HttpStatus;

public class LibraryAlreadyExistsException extends BusinessException {
    public LibraryAlreadyExistsException() {
        super("LIBRARY_ALREADY_EXISTS", HttpStatus.CONFLICT);
    }
}
