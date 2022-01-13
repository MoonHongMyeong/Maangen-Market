package me.moon.market.domain.user.exception;

import me.moon.market.global.error.exception.ErrorCode;
import me.moon.market.global.error.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

    public EmailDuplicateException(String email) {
        super(email);
    }

    public EmailDuplicateException(String email, ErrorCode errorCode) {
        super(email, errorCode);
    }
}
