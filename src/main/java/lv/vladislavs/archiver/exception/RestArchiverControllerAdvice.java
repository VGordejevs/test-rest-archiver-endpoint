package lv.vladislavs.archiver.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestArchiverControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RestArchiverException restArchiverException) {
        ErrorResponse errorResponse = new ErrorResponse(
                restArchiverException.getErrorCode(),
                restArchiverException.getMessage());
        return new ResponseEntity<>(errorResponse, restArchiverException.getHttpStatusCode());
    }
}