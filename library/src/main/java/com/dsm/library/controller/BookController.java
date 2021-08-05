package com.dsm.library.controller;

import com.dsm.library.controller.request.ModificationBookRequest;
import com.dsm.library.controller.request.RegistrationBookRequest;
import com.dsm.library.controller.response.BookResponse;
import com.dsm.library.service.book.BookModificationService;
import com.dsm.library.service.book.BookRegistrationService;
import com.dsm.library.service.book.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookRegistrationService bookRegistrationService;
    private final BookSearchService bookSearchService;
    private final BookModificationService bookModificationService;

    @GetMapping("/book")
    public BookResponse searchBooks() {
        return new BookResponse("전체 책 목록입니다.", bookSearchService.searchBooks());
    }

    @GetMapping("/books/{libraryId}")
    public BookResponse searchBook(@PathVariable Long libraryId) {
        return new BookResponse("LibraryId가 "+libraryId+"인 도서관의 책 목록입니다.",bookSearchService.searchBooksInLibrary(libraryId));
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerBook(@RequestBody RegistrationBookRequest request) throws Exception {
        bookRegistrationService.createBook(request.getTitle(), request.getLibraryId());
    }

    @PatchMapping("/book/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBook(@PathVariable Long bookId, @RequestBody ModificationBookRequest request) {
        bookModificationService.updateBook(bookId, request);
    }

}