package com.example.fileupload.payload.response;

import com.example.fileupload.entity.board.Board;
import com.example.fileupload.entity.member.Member;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private Member member;
    private String title;
    private String content;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.member = board.getMember();
        this.title = board.getTitle();
        this.content = board.getContent();
    }

}
