package org.gudartem.aars.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.gudartem.aars.model.abstraction.BaseDto;
import org.gudartem.aars.model.abstraction.BaseThemeIdDto;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Directory.DIRECTORY_NAME;
import static org.gudartem.aars.model.PojoFieldNames.Directory.DIRECTORY_TYPE;
import static org.gudartem.aars.model.PojoFieldNames.Directory.PARENT_ID;

public class DirectoryDto extends BaseThemeIdDto<UUID> {

    private String directoryName;

    private Integer directoryType;

    private UUID parentId;

    @JsonProperty(value = "children", access = JsonProperty.Access.READ_ONLY)
    private List<BaseDto<UUID>> children;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        addNullField(DIRECTORY_NAME, directoryName);
    }

    public Integer getDirectoryType() {
        return directoryType;
    }

    public void setDirectoryType(Integer directoryType) {
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

    public List<BaseDto<UUID>> getChildren() {
        return children;
    }

    public void setChildren(List<BaseDto<UUID>> children) {
        this.children = children;
    }

    @JsonProperty(value = "isDirectory", access = JsonProperty.Access.READ_ONLY)
    public Boolean getIsDirectory() {
        return Boolean.TRUE;
    }

    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return getDirectoryName();
    }
}
