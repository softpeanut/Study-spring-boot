package com.dsm.library.domain.library;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
}
