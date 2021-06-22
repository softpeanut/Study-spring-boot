package com.dsm.library.controller.response;

import com.dsm.library.domain.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class Book {
    private String title;
    private Library libraryId;
}