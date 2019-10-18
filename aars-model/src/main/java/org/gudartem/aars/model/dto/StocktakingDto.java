package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseInventoryCardIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StocktakingDto extends BaseInventoryCardIdDto<UUID> {

    private String changing;

    private String docNumber;

    private OffsetDateTime dateChanging;

    private String changedSheets;

    public String getChanging() {
        return changing;
    }

    public void setChanging(String changing) {
        this.changing = changing;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public OffsetDateTime getDateChanging() {
        return dateChanging;
    }

    public void setDateChanging(OffsetDateTime dateChanging) {
        this.dateChanging = dateChanging;
    }

    public String getChangedSheets() {
        return changedSheets;
    }

    public void setChangedSheets(String changedSheets) {
        this.changedSheets = changedSheets;
    }
}
