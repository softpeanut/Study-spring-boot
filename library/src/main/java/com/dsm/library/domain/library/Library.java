package com.dsm.library.domain.library;

import com.dsm.library.domain.book.Book;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "library")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "founding_year", nullable = false)
    private LocalDate foundingYear;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

}
