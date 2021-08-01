package com.dsm.library.service;

import com.dsm.library.domain.library.Library;
import com.dsm.library.domain.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryCreationService {
    private final LibraryRepository libraryRepository;

    public void createLibrary(String libraryName) {
        Library library = Library.builder()
                .name(libraryName)
                .foundingYear(LocalDate.now())
                .build();

        libraryRepository.save(library);
    }

}