package io.test.barogo.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 500
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템 오류 발생")

    // 404
    , BAD_REQUEST(HttpStatus.BAD_REQUEST, "파라미터가 이상합니다.")

    // 405
    , NOT_FOUND_ACCOUNTS(HttpStatus.NOT_FOUND, "계정을 찾을 수 없습니다.")

    , IS_FORBIDDEN_ACCOUTS(HttpStatus.FORBIDDEN, "계정이 존재합니다.")
    // 401
    , UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.")
    ;
    HttpStatus status;
    String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}