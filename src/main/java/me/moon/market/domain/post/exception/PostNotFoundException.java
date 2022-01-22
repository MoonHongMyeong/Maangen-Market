package me.moon.market.domain.post.exception;

import me.moon.market.global.error.exception.EntityNotFoundException;
import me.moon.market.global.error.exception.ErrorCode;

public class PostNotFoundException extends EntityNotFoundException {

    public PostNotFoundException(String message) {
        super(message, ErrorCode.POST_NOT_FOUND);
    }

    public PostNotFoundException(String message, ErrorCode code) {
        super(message, code);
    }
}
