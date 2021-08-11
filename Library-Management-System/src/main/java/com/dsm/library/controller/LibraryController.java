package com.dsm.library.controller;

import com.dsm.library.controller.request.RegistrationLibraryRequest;
import com.dsm.library.controller.response.LibraryResponse;
import com.dsm.library.service.library.LibraryCreationService;
import com.dsm.library.service.library.LibrarySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LibraryController {

    private final LibraryCreationService creationService;
    private final LibrarySearchService searchService;

    @PostMapping("/library")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerLibrary(@RequestBody RegistrationLibraryRequest request) throws Exception {
        creationService.createLibrary(request.getName());
    }

    @GetMapping("/library")
    public LibraryResponse searchLibrary() {
        return new LibraryResponse("전체 도서관 목록입니다.", searchService.searchLibrary());
    }

}