package com.dsm.library.service.library;

import com.dsm.library.domain.library.Library;
import com.dsm.library.domain.library.LibraryRepository;
import com.dsm.library.exception.LibraryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LibraryCreationService {
    private final LibraryRepository libraryRepository;

    public void createLibrary(String libraryName) {
        if(libraryRepository.existsByName(libraryName)) {
            throw new LibraryAlreadyExistsException();
        }

        Library library = Library.builder()
                .name(libraryName)
                .foundingYear(LocalDate.now())
                .build();

        libraryRepository.save(library);
    }

}