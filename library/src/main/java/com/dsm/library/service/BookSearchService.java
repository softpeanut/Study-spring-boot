package com.dsm.library.service;

import com.dsm.library.domain.Book;
import com.dsm.library.repository.BookRepository;
import com.dsm.library.repository.LibraryRepository;
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
                .orElseThrow();
    }
}