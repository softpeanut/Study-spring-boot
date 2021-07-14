package com.dms.project.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardShowResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("posts")
    private List<Board> posts;

    @AllArgsConstructor
    @Getter
    public static class Board {
        private Integer id;
        private String title;
        private String content;
        private LocalDate createdAt;
    }
}
