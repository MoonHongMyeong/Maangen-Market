package me.moon.market.domain.user.exception;

import me.moon.market.global.error.exception.ErrorCode;
import me.moon.market.global.error.exception.InvalidValueException;

public class PhoneDuplicateException extends InvalidValueException {

    public PhoneDuplicateException(String phone){
        super(phone);
    }

    public PhoneDuplicateException(String phone, ErrorCode errorCode){
        super(phone, errorCode);
    }

}
