package me.moon.market.domain.post.exception;

import me.moon.market.global.error.exception.EntityNotFoundException;
import me.moon.market.global.error.exception.ErrorCode;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(String message) {
        super(message, ErrorCode.CATEGORY_NOT_FOUND);
    }

    public CategoryNotFoundException(String message, ErrorCode code) {
        super(message, ErrorCode.CATEGORY_NOT_FOUND);
    }
}
