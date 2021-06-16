package com.dsm.library.service;

import com.dsm.library.domain.Library;
import com.dsm.library.repository.LibraryRepository;
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
                .build();   // 빌더 패턴

        libraryRepository.save(library);
    }

}