package org.gudartem.aars.api.error.model;

public enum ErrorCode {

    AARS_0001("AARS_0001", "Unexpected business error", ""),
    AARS_0002("AARS_0002",
            "Инвентарная карточка с таким номером уже существует",
            "Инвентарная карточка с номером: \"{0} {1}\" уже существует");

    private String code;
    private String message;
    private String detail;

    private ErrorCode(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
