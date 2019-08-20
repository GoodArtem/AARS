package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasThemeId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Directory.DIRECTORY_NAME;
import static org.gudartem.aars.db.constants.ColumnName.Directory.DIRECTORY_TYPE;
import static org.gudartem.aars.db.constants.ColumnName.Directory.PARENT_ID;
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;
import static org.gudartem.aars.db.constants.TableName.DIRECTORY;

@Entity
@Table(name = DIRECTORY)
public class Directory extends AbstractHasThemeId<UUID> {

    @Column(name = DIRECTORY_NAME)
    private String directoryName;

    @Column(name = DIRECTORY_TYPE)
    private String directoryType;

    @Column(name = PARENT_ID, columnDefinition = UUID_TYPE)
    private UUID parentId;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryType() {
        return directoryType;
    }

    public void setDirectoryType(String directoryType) {
        this.directoryType = directoryType;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
