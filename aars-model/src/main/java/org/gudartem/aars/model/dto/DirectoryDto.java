package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseThemeIdDto;

import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Directory.DIRECTORY_NAME;
import static org.gudartem.aars.model.PojoFieldNames.Directory.DIRECTORY_TYPE;
import static org.gudartem.aars.model.PojoFieldNames.Directory.PARENT_ID;

public class DirectoryDto extends BaseThemeIdDto<UUID> {

    private String directoryName;

    private String directoryType;

    private UUID parentId;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        addNullField(DIRECTORY_NAME, directoryName);
    }

    public String getDirectoryType() {
        return directoryType;
    }

    public void setDirectoryType(String directoryType) {
        this.directoryType = directoryType;
        addNullField(DIRECTORY_TYPE, directoryType);
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
        addNullField(PARENT_ID, parentId);
    }
}
