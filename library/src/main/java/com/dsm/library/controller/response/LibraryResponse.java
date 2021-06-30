package com.dsm.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LibraryResponse {
    private List<Library> libraries;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Library {
        private Long id;
        private String name;
        private LocalDate foundingYear;
    }
}
