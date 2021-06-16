package com.dsm.library.controller;

import com.dsm.library.controller.request.RegistrationLibraryRequest;
import com.dsm.library.controller.response.A;
import com.dsm.library.controller.response.AResponse;
import com.dsm.library.service.LibraryCreationService;
import com.dsm.library.service.LibrarySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryCreationService creationService;
    private final LibrarySearchService searchService;

    @PostMapping("/library")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerLibrary(@RequestBody RegistrationLibraryRequest request) {
        creationService.createLibrary(request.getName());
    }
    @GetMapping("/library")
    @ResponseStatus(HttpStatus.OK)
    public AResponse searchLibrary() {
        List<A> libraries = searchService.test()
                .stream()
                .map(library -> new A(library.getName(), library.getFoundingYear()))
                .collect(Collectors.toList());

        return new AResponse(libraries);
    }
}
