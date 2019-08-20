package org.gudartem.aars.db.model.abstraction;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static org.gudartem.aars.db.constants.ColumnName.HasThemeId.THEME_ID;
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;

@MappedSuperclass
public abstract class AbstractHasThemeId<T> extends AbstractHasId<T> {

    @Column(name = THEME_ID, columnDefinition = UUID_TYPE)
    private T themeId;

    public T getThemeId() {
        return themeId;
    }

    public void setThemeId(T themeId) {
        this.themeId = themeId;
    }
}
