package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasInventoryCardId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Stocktaking.CHANGED_SHEETS;
import static org.gudartem.aars.db.constants.ColumnName.Stocktaking.CHANGING;
import static org.gudartem.aars.db.constants.ColumnName.Stocktaking.DATE_CHANGING;
import static org.gudartem.aars.db.constants.ColumnName.Stocktaking.DOC_NUMBER;
import static org.gudartem.aars.db.constants.TableName.STOCKTAKING;

@Entity
@Table(name = STOCKTAKING)
public class Stocktaking extends AbstractHasInventoryCardId<UUID> {

    @Column(name = CHANGING)
    private Integer changing;

    @Column(name = DOC_NUMBER)
    private String docNumber;

    @Column(name = DATE_CHANGING)
    private OffsetDateTime dateChanging;

    @Column(name = CHANGED_SHEETS)
    private String changedSheets;

    public Integer getChanging() {
        return changing;
    }

    public void setChanging(Integer changing) {
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
