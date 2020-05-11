package org.gudartem.aars.api.error.model;

public class ErrorEntry {
    private String error;
    private String message;
    private Integer status;
    private String detail;
    private String path;
    private String debugDetail;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDebugDetail() {
        return debugDetail;
    }

    public void setDebugDetail(String debugDetail) {
        this.debugDetail = debugDetail;
    }
}
