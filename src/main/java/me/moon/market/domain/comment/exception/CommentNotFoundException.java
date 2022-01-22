package me.moon.market.domain.comment.exception;

import me.moon.market.global.error.exception.EntityNotFoundException;
import me.moon.market.global.error.exception.ErrorCode;

public class CommentNotFoundException extends EntityNotFoundException {
    public CommentNotFoundException(String message) {
        super(message, ErrorCode.COMMENT_NOT_FOUND);
    }

    public CommentNotFoundException(String message, ErrorCode code) {
        super(message, code);
    }
}
