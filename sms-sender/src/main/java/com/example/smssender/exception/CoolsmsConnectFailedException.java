package com.example.smssender.exception;

import com.example.smssender.error.ErrorCode;
import com.example.smssender.error.exception.BusinessException;

public class CoolsmsConnectFailedException extends BusinessException {
    public CoolsmsConnectFailedException() {
        super(ErrorCode.COOLSMS_CONNECT_FAILED);
    }
}
