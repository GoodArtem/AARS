package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseDto;

import java.time.OffsetDateTime;
import java.util.UUID;

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
    }

    public OffsetDateTime getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(OffsetDateTime archiveDate) {
        this.archiveDate = archiveDate;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getHasChanges() {
        return hasChanges;
    }

    public void setHasChanges(String hasChanges) {
        this.hasChanges = hasChanges;
    }
}
