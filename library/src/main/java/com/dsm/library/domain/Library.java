package com.dsm.library.domain;

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
    //@Column(name = "id")
    private Long id;

    //@Column(name = "name")
    private String name;

    @Column(name = "founding_year")
    private LocalDate foundingYear;
}
