package com.dsm.library.exception;

import org.springframework.http.HttpStatus;

public class LibraryNotFoundException extends BusinessException {
    public LibraryNotFoundException(long libraryId) {
        super("LIBRARY_NOT_FOUND", "LibraryId가 " + libraryId + "인 도서관을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
