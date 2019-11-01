package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseInventoryCardIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Applicability.APPLICABILITY_DATE;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.APP_INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.CIPHER;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.DESIGNATION;

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
        addNullField(APPLICABILITY_DATE, applicabilityDate);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
        addNullField(DESIGNATION, designation);
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
        addNullField(CIPHER, cipher);
    }

    public UUID getAppInventoryCardId() {
        return appInventoryCardId;
    }

    public void setAppInventoryCardId(UUID appInventoryCardId) {
        this.appInventoryCardId = appInventoryCardId;
        addNullField(APP_INVENTORY_CARD_ID, appInventoryCardId);
    }
}
