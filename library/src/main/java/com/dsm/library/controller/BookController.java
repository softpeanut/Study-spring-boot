package com.dsm.library.controller;

import com.dsm.library.controller.request.RegistrationBookRequest;
import com.dsm.library.service.BookRegistrationService;
import com.dsm.library.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.dsm.library.controller.response.BookResponse;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRegistrationService bookRegistrationService;
    private final BookSearchService bookSearchService;


    @PostMapping("/book")
    public void registerBook(@RequestBody RegistrationBookRequest request) throws Exception {
        bookRegistrationService.createBook(request.getTitle(), request.getLibraryId());
    }

    @GetMapping("/book")
    public BookResponse searchBooks() {
        return new BookResponse(
                bookSearchService.searchBooks()
                        .stream()
                        .map(book -> new BookResponse.BookInformation(book.getBookId(), book.getTitle(), book.getLibrary().getName()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/book/{id}")
    public BookResponse searchBook(@PathVariable Long id) {
        return new BookResponse(
                bookSearchService.searchBook(id)
                    .stream()
                    .map(book -> new BookResponse.BookInformation(book.getBookId(), book.getTitle(), book.getLibrary().getName()))
                    .collect(Collectors.toList())
        );
    }

}