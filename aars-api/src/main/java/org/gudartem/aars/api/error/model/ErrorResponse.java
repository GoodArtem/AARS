package org.gudartem.aars.api.error.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorResponse {
    private List<ErrorEntry> errors = new ArrayList<>();

    public List<ErrorEntry> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorEntry> errors) {
        Objects.requireNonNull(errors, "ErrorEntry list cannot be null");
        this.errors = errors;
    }

    public ErrorResponse addError(ErrorEntry error){
        errors.add(error);
        return this;
    }
}
