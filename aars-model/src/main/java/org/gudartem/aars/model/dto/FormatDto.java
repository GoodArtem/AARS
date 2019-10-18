package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseDto;

import java.util.UUID;

public class FormatDto extends BaseDto<UUID> {

    private String formatName;

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }
}
