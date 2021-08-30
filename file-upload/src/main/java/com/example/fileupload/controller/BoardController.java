package com.example.fileupload.controller;

import com.example.fileupload.payload.request.BoardCreateRequestDto;
import com.example.fileupload.payload.request.BoardUpdateRequestDto;
import com.example.fileupload.payload.response.BoardListResponseDto;
import com.example.fileupload.payload.response.BoardResponseDto;
import com.example.fileupload.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public Long create(@RequestPart BoardCreateRequestDto requestDto) {
        return boardService.create(requestDto);
    }

    @PutMapping("/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto searchById(@PathVariable Long id) {
        return boardService.searchById(id);
    }

    @GetMapping("/board")
    public List<BoardListResponseDto> searchAllDesc() {
        return boardService.searchAllDesc();
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }

}
