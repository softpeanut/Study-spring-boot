package com.dms.project.service.show;

import com.dms.project.payload.response.BoardShowResponse;

import java.util.List;

public interface BoardShowService {
    List<BoardShowResponse.Board> show();
}
