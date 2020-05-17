package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasThemeId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
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
    private Integer directoryType;

    @Column(name = PARENT_ID, columnDefinition = UUID_TYPE)
    private UUID parentId;

    @Transient
    private List<Directory> childDirectoryList;

    @Transient
    private List<InventoryCard> childInventoryCardList;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public Integer getDirectoryType() {
        return directoryType;
    }

    public void setDirectoryType(Integer directoryType) {
        this.directoryType = directoryType;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public List<Directory> getChildDirectoryList() {
        return childDirectoryList;
    }

    public void setChildDirectoryList(List<Directory> childDirectoryList) {
        this.childDirectoryList = childDirectoryList;
    }

    public List<InventoryCard> getChildInventoryCardList() {
        return childInventoryCardList;
    }

    public void setChildInventoryCardList(List<InventoryCard> childInventoryCardList) {
        this.childInventoryCardList = childInventoryCardList;
    }
}
