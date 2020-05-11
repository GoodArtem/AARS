package org.gudartem.aars.web.error.handling;

import org.gudartem.aars.api.error.exception.RestClientException;
import org.gudartem.aars.api.error.model.ErrorResponse;
import org.gudartem.aars.api.error.utils.ErrorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClientException(RestClientException ex) {
        return new ResponseEntity<>(ex.getErrorResponse(),
                HttpStatus.resolve(ex.getErrorResponse().getErrors().get(0).getStatus()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ResponseEntity responseEntity = new ResponseEntity<>(ErrorUtils.getDefaultErrorResponse(ex),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
