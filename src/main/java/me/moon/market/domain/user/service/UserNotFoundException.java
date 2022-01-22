package me.moon.market.domain.user.service;

import me.moon.market.global.error.exception.EntityNotFoundException;
import me.moon.market.global.error.exception.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, ErrorCode code) {
        super(message, code);
    }


}
