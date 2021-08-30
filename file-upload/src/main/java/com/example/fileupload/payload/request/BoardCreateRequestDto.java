package com.example.fileupload.payload.request;

import com.example.fileupload.entity.board.Board;
import com.example.fileupload.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class BoardCreateRequestDto {
    private Member member;
    private String title;
    private String content;

    public Board toEntity() {
        return Board.item()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }

}
