package com.dsm.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Library {
    private String name;
    private LocalDate foundingYear;
}
