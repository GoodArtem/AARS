package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseDto;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Theme.ARCHIVE_DATE;
import static org.gudartem.aars.model.PojoFieldNames.Theme.CIPHER;
import static org.gudartem.aars.model.PojoFieldNames.Theme.HAS_CHANGES;
import static org.gudartem.aars.model.PojoFieldNames.Theme.THEME_NAME;

public class ThemeDto extends BaseDto<UUID> {

    private String themeName;

    private OffsetDateTime archiveDate;

    private String cipher;

    private String hasChanges;

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
        addNullField(THEME_NAME, themeName);
    }

    public OffsetDateTime getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(OffsetDateTime archiveDate) {
        this.archiveDate = archiveDate;
        addNullField(ARCHIVE_DATE, archiveDate);
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
        addNullField(CIPHER, cipher);
    }

    public String getHasChanges() {
        return hasChanges;
    }

    public void setHasChanges(String hasChanges) {
        this.hasChanges = hasChanges;
        addNullField(HAS_CHANGES, hasChanges);
    }
}
