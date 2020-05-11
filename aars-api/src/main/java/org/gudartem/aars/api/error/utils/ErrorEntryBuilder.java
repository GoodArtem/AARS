package org.gudartem.aars.api.error.utils;

import org.gudartem.aars.api.error.model.ErrorEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class ErrorEntryBuilder {

    private final Logger logger = LoggerFactory.getLogger(ErrorEntryBuilder.class);

    private ErrorEntry errorEntry;

    public ErrorEntryBuilder() {}

    public ErrorEntryBuilder create() {
        this.errorEntry = new ErrorEntry();
        return this;
    }

    public ErrorEntryBuilder error(String error) {
        this.errorEntry.setError(error);
        return this;
    }

    public ErrorEntryBuilder message(String message) {
        this.errorEntry.setMessage(message);
        return this;
    }

    public ErrorEntryBuilder status(HttpStatus status) {
        this.errorEntry.setStatus(status.value());
        return this;
    }

    public ErrorEntryBuilder detail(String detail) {
        this.errorEntry.setDetail(detail);
        return this;
    }

    public ErrorEntryBuilder detail(String detail, Object...detailParameters) {
        if (detailParameters != null) {
            for (int i = 0; i < detailParameters.length; i++) {
                logger.info("detailParameter[" + i + "]:" + detailParameters[i]);
            }
        }
        logger.info("Formatted message: " + MessageFormat.format(detail, detailParameters));
        this.errorEntry.setDetail(detailParameters == null
                ? detail
                : MessageFormat.format(detail, detailParameters));
        return this;
    }

    public ErrorEntryBuilder path(String path) {
        this.errorEntry.setPath(path);
        return this;
    }

    public ErrorEntryBuilder debugDetail(String debugDetail) {
        this.errorEntry.setDebugDetail(debugDetail);
        return this;
    }

    public ErrorEntry build() {
        return this.errorEntry;
    }
}
