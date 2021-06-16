package com.dsm.library.service;

import com.dsm.library.domain.Book;
import com.dsm.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSearchService {
    private final BookRepository bookRepository;

    public List<Book> test() {
        return bookRepository.findAll();
    }

}
