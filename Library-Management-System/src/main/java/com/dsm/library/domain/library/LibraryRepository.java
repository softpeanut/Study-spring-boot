package com.dsm.library.domain.library;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    boolean existsByName(String name);
}
