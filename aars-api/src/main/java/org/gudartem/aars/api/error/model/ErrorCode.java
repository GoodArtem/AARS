package org.gudartem.aars.api.error.model;

public enum ErrorCode {

    AARS_0001("AARS_0001", "Unexpected business error", ""),
    AARS_0002("AARS_0002",
            "Инвентарная карточка с таким номером уже существует",
            "Инвентарная карточка с номером: \"{0} {1}\" уже существует"),
    AARS_0003("AARS_0003",
            "Изменение уже существует",
            "Изменение с отметкой \"Нов.\" уже существует"),
    AARS_0004("AARS_0004",
            "Некорректная дата изменения",
            "Дата изменения с отметкой \"Нов.\" должна быть меньше самого раннего изменения"),
    AARS_0005("AARS_0005",
            "Некорректная дата изменения",
            "Дата изменения не может быть меньше измнения с отметкой \"Нов.\"");

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
