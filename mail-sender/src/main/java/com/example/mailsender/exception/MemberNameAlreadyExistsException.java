package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class MemberNameAlreadyExistsException extends BusinessException {
    public MemberNameAlreadyExistsException() {
        super(ErrorCode.MEMBER_NAME_ALREADY_EXISTS);
    }
}
