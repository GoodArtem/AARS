package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseDto;

import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Format.FORMAT_NAME;

public class FormatDto extends BaseDto<UUID> {

    private String formatName;

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
        addNullField(FORMAT_NAME, formatName);
    }
}
