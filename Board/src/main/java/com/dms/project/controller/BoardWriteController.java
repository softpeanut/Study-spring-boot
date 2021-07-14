package com.dms.project.controller;

import com.dms.project.payload.request.BoardRequest;
import com.dms.project.payload.response.BoardWriteResponse;
import com.dms.project.service.create.BoardCreationServiceImpl;
import com.dms.project.service.delete.BoardDeletionServiceImpl;
import com.dms.project.service.modify.BoardModificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@CrossOrigin(origins = "*")
public class BoardWriteController {
    private final BoardCreationServiceImpl boardCreationService;
    private final BoardModificationServiceImpl boardModifyService;
    private final BoardDeletionServiceImpl boardDeleteService;

    @PostMapping
    public BoardWriteResponse write(@RequestBody BoardRequest request) {
        return boardCreationService.createBoard(request.getTitle(), request.getContent());
    }

    @PatchMapping("/{feed_id}")
    public BoardWriteResponse modify(@PathVariable(name = "feed_id") Integer id, @RequestBody BoardRequest request) {
        return boardModifyService.modifyBoard(request, id);
    }

    @DeleteMapping("/{feed_id}")
    public BoardWriteResponse delete(@PathVariable(name = "feed_id") Integer id) {
        return boardDeleteService.deleteBoard(id);
    }

}
