package com.dms.project.service.delete;

import com.dms.project.payload.response.BoardWriteResponse;

public interface BoardDeleteService {
    BoardWriteResponse deleteBoard(Integer id);
}
