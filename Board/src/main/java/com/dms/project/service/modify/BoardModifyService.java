package com.dms.project.service.modify;

import com.dms.project.payload.request.BoardRequest;
import com.dms.project.payload.response.BoardWriteResponse;

public interface BoardModifyService {
    BoardWriteResponse modifyBoard(BoardRequest request, Integer id);
}
