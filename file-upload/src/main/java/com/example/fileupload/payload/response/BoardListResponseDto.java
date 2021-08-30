package com.example.fileupload.payload.response;

import com.example.fileupload.entity.board.Board;
import lombok.Getter;

@Getter
public class BoardListResponseDto {
    private Long id;
    private String member;
    private String title;

    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.member = board.getMember().getName();
        this.title = board.getTitle();
    }

}
