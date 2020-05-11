package org.gudartem.aars.api.error.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.gudartem.aars.api.error.model.ErrorResponse;

public class RestClientException extends RuntimeException {

    private ErrorResponse errorResponse;

    public RestClientException(ErrorResponse errorResponse) {
        super();
        this.errorResponse = enrichErrorResponse(errorResponse);
    }

    public RestClientException(ErrorResponse errorResponse, String message) {
        super(message);
        this.errorResponse = enrichErrorResponse(errorResponse);
    }

    public RestClientException(ErrorResponse errorResponse, String message, Throwable cause) {
        super(message, cause);
        this.errorResponse = enrichErrorResponse(errorResponse);
    }

    public RestClientException(ErrorResponse errorResponse, Throwable cause) {
        super(cause);
        this.errorResponse = enrichErrorResponse(errorResponse);
    }

    protected RestClientException(ErrorResponse errorResponse, String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorResponse = enrichErrorResponse(errorResponse);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    private ErrorResponse enrichErrorResponse(ErrorResponse source) {
        source.getErrors().get(0).setDebugDetail(ExceptionUtils.getStackTrace(this));
        return source;
    }
}
