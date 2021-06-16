package com.dsm.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class LibraryResponse {
    private List<Library> libraries;
}
