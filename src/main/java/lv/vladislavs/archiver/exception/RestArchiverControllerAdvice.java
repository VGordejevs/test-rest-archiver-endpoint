package lv.vladislavs.archiver.exception;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestArchiverControllerAdvice {
    private final static String FILE_SIZE_LIMIT_EXCEEDED_ERROR = "FILE_SIZE_LIMIT_EXCEEDED_ERROR";

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RestArchiverException restArchiverException) {
        ErrorResponse errorResponse = new ErrorResponse(
                restArchiverException.getErrorCode(),
                restArchiverException.getMessage());
        return new ResponseEntity<>(errorResponse, restArchiverException.getHttpStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SizeLimitExceededException sizeLimitExceededException) {
        ErrorResponse errorResponse = new ErrorResponse(
                FILE_SIZE_LIMIT_EXCEEDED_ERROR,
                sizeLimitExceededException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}