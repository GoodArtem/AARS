package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseInventoryCardIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ApplicabilityDto extends BaseInventoryCardIdDto<UUID> {

    private OffsetDateTime applicabilityDate;

    private String designation;

    private String cipher;

    private UUID appInventoryCardId;

    public OffsetDateTime getApplicabilityDate() {
        return applicabilityDate;
    }

    public void setApplicabilityDate(OffsetDateTime applicabilityDate) {
        this.applicabilityDate = applicabilityDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public UUID getAppInventoryCardId() {
        return appInventoryCardId;
    }

    public void setAppInventoryCardId(UUID appInventoryCardId) {
        this.appInventoryCardId = appInventoryCardId;
    }
}
