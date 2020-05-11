package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasInventoryCardId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Applicability.APPLICABILITY_DATE;
import static org.gudartem.aars.db.constants.ColumnName.Applicability.APP_INVENTORY_CARD_ID;
import static org.gudartem.aars.db.constants.ColumnName.Applicability.CIPHER;
import static org.gudartem.aars.db.constants.ColumnName.Applicability.DESIGNATION;
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;
import static org.gudartem.aars.db.constants.TableName.APPLICABILITY;

@Entity
@Table(name = APPLICABILITY)
public class Applicability extends AbstractHasInventoryCardId<UUID> {

    @Column(name = APPLICABILITY_DATE)
    private OffsetDateTime applicabilityDate;

    private String designation;

    private String cipher;

    @Column(name = APP_INVENTORY_CARD_ID, columnDefinition = UUID_TYPE)
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
