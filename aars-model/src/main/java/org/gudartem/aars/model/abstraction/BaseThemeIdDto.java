package org.gudartem.aars.model.abstraction;

public class BaseThemeIdDto<T> extends BaseDto<T> {
    private T themeId;

    public T getThemeId() {
        return themeId;
    }

    public void setThemeId(T themeId) {
        this.themeId = themeId;
    }
}
