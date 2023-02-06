package io.test.barogo.support;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse extends RuntimeException {
    ErrorCode code;
    String message;

    public static ErrorResponse of(ErrorCode code) {
        ErrorResponse instance = new ErrorResponse();
        instance.code = code;
        instance.message = code.getMessage();
        return instance;
    }

    public static ErrorResponse of(ErrorCode code, String message) {
        ErrorResponse instance = new ErrorResponse();
        instance.code = code;
        instance.message = message;
        return instance;
    }
}
