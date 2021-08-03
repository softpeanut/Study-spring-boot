package com.dsm.library.exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BusinessException {
    public BookNotFoundException(Long bookId) {
        super("BOOK_NOT_FOUND", "BookId가 "+bookId+"인 책을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
