package com.dms.project.controller;

import com.dms.project.payload.response.BoardShowResponse;
import com.dms.project.service.show.BoardShowServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoardShowController {

    private final BoardShowServiceImpl boardSearchService;

    @GetMapping("/feed")
    public BoardShowResponse show() {
        return new BoardShowResponse("feed read access", boardSearchService.show());
    }

}
