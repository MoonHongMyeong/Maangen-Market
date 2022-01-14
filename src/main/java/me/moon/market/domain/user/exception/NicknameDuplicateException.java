package me.moon.market.domain.user.exception;

import me.moon.market.global.error.exception.ErrorCode;
import me.moon.market.global.error.exception.InvalidValueException;

public class NicknameDuplicateException extends InvalidValueException {
    public NicknameDuplicateException(String nickname) {
        super(nickname);
    }

    public NicknameDuplicateException(String nickname, ErrorCode errorCode) {
        super(nickname, errorCode);
    }
}
