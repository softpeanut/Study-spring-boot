package com.dsm.library.domain.book;

import com.dsm.library.domain.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByLibrary(Library library);
}
