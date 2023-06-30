package com.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {


    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰 만료"),


    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "유효하지 않은 token"),
    NOT_EXIST_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "header에 Authorization이 없습니다"),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 bearer 타입이 아닙니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "저장된 refresh token이 없습니다."),

    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", " refresh token이 만료되었습니다."),

    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", " 해당 토큰은 access 타입이 아닙니다."),

    FORBIDDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008", " admin 권한이 없습니다."),

    //사용자 잘못 입력경우
    NOT_EXIST_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "M-001", " 잘못 된 회원타입입니다."),
    ALREADY_REGISTED_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입된 회원입니다."),
    NOT_FOUND_MEMBER_ID(HttpStatus.BAD_REQUEST, "M-003", "회원 아이디가 없습니다.");


    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

}
