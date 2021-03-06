package me.moon.market.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //Common
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "알 수 없는 에러\n 관리자에게 연락해주세요."),
    INVALID_INPUT_VALUE(400, "Invalid Input Value", "잘못된 input 값 입니다."),
    ENTITY_NOT_FOUND(400,"Entity Not Found", "Entity 를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "허용되지 않은 메소드"),
    HANDLE_ACCESS_DENIED(403, "Handle Access Denied", "엑세스가 거부되었습니다."),
    INVALID_TYPE_VALUE(400, "Invalid Type Value", "잘못된 type 입니다."),

    //User
    UNAUTHORIZED_ACCESS(401, "Unautorized Access", "잘못된 사용자 접근입니다."),
    EMAIL_DUPLICATED(409, "Email Duplicated", "중복된 이메일 입니다."),
    PHONE_DUPLICATED(409, "Phone number Duplicated", "중복된 전화번호 입니다."),
    NICKNAME_DUPLICATED(409, "Nickname Duplicated", "중복된 닉네임 입니다."),
    USER_NOT_FOUND(409, "User Not Found", "찾을 수 없는 사용자 입니다."),
    WRONG_LOGIN(400, "Wrong login value", "아이디 혹은 비밀번호가 잘못되었습니다."),

    //Category
    CATEGORY_NOT_FOUND(400, "Category Not Found", "카테고리를 찾을 수 없습니다."),

    //Post
    POST_NOT_FOUND(400, "Post Not Found", "해당 포스트를 찾을 수 없습니다."),

    //Comment
    COMMENT_NOT_FOUND(400, "Comment Not Found", "해당 댓글을 찾을 수 없습니다.")
    ;

    private final int status;
    private final String code;
    private final String message;


    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
