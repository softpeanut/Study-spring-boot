package com.dms.project.service.create;

import com.dms.project.entity.Board;
import com.dms.project.entity.BoardRepository;
import com.dms.project.payload.response.BoardWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class BoardCreationServiceImpl implements BoardCreationService{
    private final BoardRepository boardRepository;

    @Override
    public BoardWriteResponse createBoard(String title, String content) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDate.now())
                .build();

        boardRepository.save(board);
        return new BoardWriteResponse("feed create success", BoardWriteResponse.Board.builder()
                .id(board.getId())
                .title(title)
                .content(content)
                .createdAt(board.getCreatedAt())
                .build());
    }

}
