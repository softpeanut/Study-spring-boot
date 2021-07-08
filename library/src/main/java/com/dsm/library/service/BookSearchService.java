package com.dsm.library.service;

import com.dsm.library.domain.book.Book;
import com.dsm.library.domain.book.BookRepository;
import com.dsm.library.domain.library.LibraryRepository;
import com.dsm.library.exception.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSearchService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public List<Book> searchBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBook(Long id) {
        return libraryRepository.findById(id)
                .map(bookRepository::findAllByLibrary)
                .orElseThrow(() -> new LibraryNotFoundException(id));
    }
}