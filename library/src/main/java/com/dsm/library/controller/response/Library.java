package com.dsm.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Library {
    private Long id;
    private String name;
    private LocalDate foundingYear;
}
