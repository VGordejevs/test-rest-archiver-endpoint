package lv.vladislavs.archiver.exception;

import org.springframework.http.HttpStatus;

public class RestArchiverException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatusCode;

    public RestArchiverException(String code, HttpStatus httpStatusCode) {
        this(code, httpStatusCode.getReasonPhrase(), httpStatusCode);
    }

    public RestArchiverException(String code, String message, HttpStatus httpStatusCode) {
        super(message);
        this.errorCode = code;
        this.httpStatusCode = httpStatusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}