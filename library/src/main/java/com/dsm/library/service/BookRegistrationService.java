package com.dsm.library.service;

import com.dsm.library.domain.book.Book;
import com.dsm.library.domain.library.Library;
import com.dsm.library.domain.book.BookRepository;
import com.dsm.library.domain.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRegistrationService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public void createBook(String title, long libraryId) throws Exception {
            Library library = libraryRepository.findById(libraryId)
                    .orElseThrow(Exception::new);
            Book book = new Book(null, title, library);

            bookRepository.save(book);
    }
}