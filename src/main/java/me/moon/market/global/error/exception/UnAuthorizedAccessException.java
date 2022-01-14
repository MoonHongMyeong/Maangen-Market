package me.moon.market.global.error.exception;

public class UnAuthorizedAccessException extends BusinessException {

    public UnAuthorizedAccessException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
