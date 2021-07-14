package com.dms.project.service.delete;

import com.dms.project.payload.response.BoardWriteResponse;

public interface BoardDeletionService {
    BoardWriteResponse deleteBoard(Integer id);
}
