package com.dsm.library.repository;

import com.dsm.library.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
