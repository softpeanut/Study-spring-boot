package com.dsm.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookResponse {
    private List<BookInformation> books;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class BookInformation {
        private Long bookId;
        private String title;
        private String library;
    }
}

