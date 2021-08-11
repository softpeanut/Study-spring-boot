package com.dsm.library.service.book;

import com.dsm.library.controller.response.BookResponse;
import com.dsm.library.domain.book.Book;
import com.dsm.library.domain.book.BookRepository;
import com.dsm.library.domain.library.LibraryRepository;
import com.dsm.library.exception.LibraryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookSearchService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public List<BookResponse.BookInformation> searchBooks() {
        List<BookResponse.BookInformation> books = bookRepository.findAll()
                .stream()
                .map(book -> new BookResponse.BookInformation(book.getBookId(), book.getTitle(), book.getLibrary().getName()))
                .collect(Collectors.toList());

        return books;
    }

    public List<BookResponse.BookInformation> searchBooksInLibrary (Long libraryId) {
        List<BookResponse.BookInformation> bookInformations = showBooks(libraryId)
                .stream()
                .map(book -> new BookResponse.BookInformation(book.getBookId(), book.getTitle(), book.getLibrary().getName()))
                .collect(Collectors.toList());

        return bookInformations;
    }

    public List<Book> showBooks(Long libraryId) {
        List<Book> books = libraryRepository.findById(libraryId)
                .map(bookRepository::findAllByLibrary)
                .orElseThrow(LibraryNotFoundException::new);
        return books;
    }
}