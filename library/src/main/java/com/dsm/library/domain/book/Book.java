package com.dsm.library.domain.book;

import com.dsm.library.domain.library.Library;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Setter
    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;

}
