package com.dsm.library.service;

import com.dsm.library.domain.Book;
import com.dsm.library.domain.Library;
import com.dsm.library.repository.BookRepository;
import com.dsm.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor// 나중에 DI 이론이나 Lombok에 대해서 더 공부할것
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