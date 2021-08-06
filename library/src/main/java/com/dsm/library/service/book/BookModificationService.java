package com.dsm.library.service.book;

import com.dsm.library.controller.request.ModificationBookRequest;
import com.dsm.library.domain.book.BookRepository;
import com.dsm.library.domain.library.LibraryRepository;
import com.dsm.library.exception.BookNotFoundException;
import com.dsm.library.exception.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookModificationService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public void updateBook(Long bookId, ModificationBookRequest request) {
        bookRepository.findById(bookId)
                .map(book -> {
                    book.setTitle(request.getTitle());
                    book.setLibrary(
                            libraryRepository.findById(request.getLibraryId()).orElseThrow(LibraryNotFoundException::new)
                    );
                    bookRepository.save(book);
                    return book;
                })
                .orElseThrow(BookNotFoundException::new);
    }

}
