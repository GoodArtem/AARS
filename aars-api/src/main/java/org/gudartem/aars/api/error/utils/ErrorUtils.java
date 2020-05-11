package org.gudartem.aars.api.error.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.gudartem.aars.api.constants.ErrorCodes;
import org.gudartem.aars.api.constants.MDCConstants;
import org.gudartem.aars.api.error.exception.RestClientException;
import org.gudartem.aars.api.error.model.ErrorCode;
import org.gudartem.aars.api.error.model.ErrorResponse;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ErrorUtils {

    public static RestClientException getUnprocessableEntityException(ErrorCode errorCode) {
        return getUnprocessableEntityException(errorCode, null);
    }

    public static RestClientException getUnprocessableEntityException(ErrorCode errorCode, Object...detailParameters) {
        return getUnprocessableEntityException(errorCode.getCode(), errorCode.getMessage(), errorCode.getDetail(), detailParameters);
    }

    public static RestClientException getUnprocessableEntityException(String error, String message, String detail, Object...detailParameters) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorEntryBuilder entryBuilder = new ErrorEntryBuilder();
        errorResponse.addError(entryBuilder.create()
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .error(error)
                .message(message)
                .detail(detail, detailParameters)
                .path(MDC.get(MDCConstants.ENDPOINT_PATH))
                .build());
        return new RestClientException(errorResponse);
    }

    public static RestClientException getNotFoundException(String error, String message, String detail) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorEntryBuilder entryBuilder = new ErrorEntryBuilder();
        errorResponse.addError(entryBuilder.create()
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .error(error)
                .message(message)
                .detail(detail)
                .path(MDC.get(MDCConstants.ENDPOINT_PATH))
                .build());
        return new RestClientException(errorResponse);
    }

    public static ErrorResponse getDefaultErrorResponse(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorEntryBuilder entryBuilder = new ErrorEntryBuilder();
        ErrorCode code = ErrorCode.AARS_0001;
        errorResponse.addError(entryBuilder.create()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(code.getCode())
                .message(code.getMessage())
                .detail(code.getDetail())
                .path(MDC.get(MDCConstants.ENDPOINT_PATH))
                .debugDetail(ExceptionUtils.getStackTrace(ex))
                .build());
        return errorResponse;
    }
}
