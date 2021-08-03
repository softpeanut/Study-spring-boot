package com.dsm.library.service.book;

import com.dsm.library.controller.request.ModificationBookRequest;
import com.dsm.library.domain.book.BookRepository;
import com.dsm.library.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookModificationService {

    private final BookRepository bookRepository;

    public void updateBook(Long bookId, ModificationBookRequest request) {
        bookRepository.findById(bookId)
                .map(book -> {
                    book.setTitle(request.getTitle());
                    bookRepository.save(book);
                    return book;
                })
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

}
