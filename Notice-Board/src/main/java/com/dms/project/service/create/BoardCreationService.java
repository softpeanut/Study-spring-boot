package com.dms.project.service.create;

import com.dms.project.payload.response.BoardWriteResponse;

public interface BoardCreationService {
    BoardWriteResponse createBoard(String title, String content);
}
