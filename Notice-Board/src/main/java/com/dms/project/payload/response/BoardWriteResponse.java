package com.dms.project.payload.response;

import com.dms.project.entity.Board;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Library;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardWriteResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("feed")
    private final Board board;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Board {
        private Integer id;
        private String title;
        private String content;
        private LocalDate createdAt;
    }
}
