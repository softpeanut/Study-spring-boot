package com.dms.project.service.delete;

import com.dms.project.entity.Board;
import com.dms.project.entity.BoardRepository;
import com.dms.project.payload.response.BoardWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeletionServiceImpl implements BoardDeletionService {
    private final BoardRepository boardRepository;

    @Override
    public BoardWriteResponse deleteBoard(Integer id) {
        Board board = boardRepository.getById(id);

        boardRepository.deleteById(id);

        return new BoardWriteResponse   ("feed delete success", BoardWriteResponse.Board.builder()
                .id(id)
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build());
    }
}
