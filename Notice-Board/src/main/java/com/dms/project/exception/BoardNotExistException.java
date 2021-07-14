package com.dms.project.exception;

import org.springframework.http.HttpStatus;


public class BoardNotExistException extends BusinessException {
    public BoardNotExistException(Integer boardId) {
        super("BOARD_NOT_EXIST", "BoardId가 " + boardId + "인 글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
