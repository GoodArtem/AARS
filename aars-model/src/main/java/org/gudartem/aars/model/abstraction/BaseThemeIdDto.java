package org.gudartem.aars.model.abstraction;

import static org.gudartem.aars.model.PojoFieldNames.HasThemeId.THEME_ID;

public class BaseThemeIdDto<T> extends BaseDto<T> {
    private T themeId;

    public T getThemeId() {
        return themeId;
    }

    public void setThemeId(T themeId) {
        this.themeId = themeId;
        addNullField(THEME_ID, themeId);
    }
}
