package com.dsm.library.controller;

import com.dsm.library.controller.request.RegistrationBookRequest;
import com.dsm.library.controller.response.BookResponse;
import com.dsm.library.service.BookRegistrationService;
import com.dsm.library.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookRegistrationService bookRegistrationService;
    private final BookSearchService bookSearchService;


    @PostMapping("/book")
    public void registerBook(@RequestBody RegistrationBookRequest request) throws Exception {
        bookRegistrationService.createBook(request.getTitle(), request.getLibraryId());
    }
    @GetMapping("/book")
    public BookResponse searchBooks() {
        return new BookResponse(bookSearchService.searchBooks());
    }

    @GetMapping("/books/{libraryId}")
    public BookResponse searchBook(@PathVariable Long libraryId) {
        return new BookResponse(bookSearchService.searchBooksInLibrary(libraryId));
    }

}