package com.dsm.library.controller.request;

import com.dsm.library.domain.library.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModificationBookRequest {
    private String title;
    private Library libraryId;
}
