package com.dms.project.service.show;

import com.dms.project.payload.response.BoardShowResponse;
import com.dms.project.entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardShowServiceImpl implements BoardShowService{
    private final BoardRepository boardRepository;

    @Override
    public List<BoardShowResponse.Board> show() {
        List<BoardShowResponse.Board> boards = boardRepository.findAll()
                .stream()
                .map(board -> new BoardShowResponse.Board(board.getId(), board.getTitle(), board.getContent(), board.getCreatedAt()))
                .collect(Collectors.toList());
        return boards;
    }

}
