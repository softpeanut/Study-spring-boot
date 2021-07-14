package com.dms.project.service.modify;

import com.dms.project.entity.BoardRepository;
import com.dms.project.exception.BoardNotExistException;
import com.dms.project.payload.request.BoardRequest;
import com.dms.project.payload.response.BoardWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class BoardModifyServiceImpl implements BoardModifyService {
    private final BoardRepository boardRepository;

    @Override
    public BoardWriteResponse modifyBoard(BoardRequest request, Integer id) {
        boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(request.getTitle());
                    board.setContent(request.getContent());
                    board.setCreatedAt(LocalDate.now());
                    boardRepository.save(board);
                    return board;
                })
                .orElseThrow(() -> new BoardNotExistException(id));
        return new BoardWriteResponse("feed update success", BoardWriteResponse.Board.builder()
                .id(id)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDate.now())
                .build());
    }
}

