package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Theme.ARCHIVE_DATE;
import static org.gudartem.aars.db.constants.ColumnName.Theme.CIPHER;
import static org.gudartem.aars.db.constants.ColumnName.Theme.HAS_CHANGES;
import static org.gudartem.aars.db.constants.ColumnName.Theme.THEME_NAME;
import static org.gudartem.aars.db.constants.TableName.THEME;

@Entity
@Table(name = THEME)
public class Theme extends AbstractHasId<UUID> {

    @Column(name = THEME_NAME)
    private String themeName;

    @Column(name = ARCHIVE_DATE)
    private OffsetDateTime archiveDate;

    @Column(name = CIPHER)
    private String cipher;

    @Column(name = HAS_CHANGES)
    private String hasChanges;

    @Transient
    private List<Directory> children;

    public Theme() {
        this.children = new ArrayList<>();
    }

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

    public List<Directory> getChildren() {
        return children;
    }

    public void setChildren(List<Directory> children) {
        this.children = children;
    }
}
