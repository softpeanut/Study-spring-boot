package com.example.fileupload.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String content;
}
