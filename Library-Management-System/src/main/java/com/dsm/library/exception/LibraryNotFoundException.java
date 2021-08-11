package com.dsm.library.exception;

import org.springframework.http.HttpStatus;

public class LibraryNotFoundException extends BusinessException {
    public LibraryNotFoundException() {
        super("LIBRARY_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
