package com.dsm.library.repository;

import com.dsm.library.domain.Book;
import com.dsm.library.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByLibrary(Library library);
}
