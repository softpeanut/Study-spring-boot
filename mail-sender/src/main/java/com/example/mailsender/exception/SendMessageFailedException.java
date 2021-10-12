package com.example.mailsender.exception;

import com.example.mailsender.error.ErrorCode;
import com.example.mailsender.error.exception.BusinessException;

public class SendMessageFailedException extends BusinessException {
    public SendMessageFailedException() {
        super(ErrorCode.SEND_MESSAGE_FAILED);
    }
}
