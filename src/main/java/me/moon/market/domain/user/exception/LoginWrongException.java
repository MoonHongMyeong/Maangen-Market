package me.moon.market.domain.user.exception;

import me.moon.market.global.error.exception.ErrorCode;
import me.moon.market.global.error.exception.InvalidValueException;

public class LoginWrongException extends InvalidValueException {
    public LoginWrongException(String value) {
        super(value);
    }

    public LoginWrongException(String value, ErrorCode errorCode) {
        super(value, errorCode.WRONG_LOGIN);
    }
}
