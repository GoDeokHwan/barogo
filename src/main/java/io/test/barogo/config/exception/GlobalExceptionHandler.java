package io.test.barogo.config.exception;

import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        ErrorResponse ex = null;
        if (e instanceof ErrorResponse) {
            ex = (ErrorResponse) e;
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            StringBuilder message = new StringBuilder();
            message.append("파라미터가 잘못되었습니다. \n[");
            for (FieldError fieldError : me.getFieldErrors()) {
                message.append(fieldError.getDefaultMessage());
                message.append(",");
            }
            message.delete(message.length() -1 ,message.length());
            message.append("]");
            ex = ErrorResponse.of(ErrorCode.BAD_REQUEST, message.toString());
        } else {
            ex = ErrorResponse.of(ErrorCode.SERVER_ERROR);
        }
        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(ex.getMessage());
    }
}
