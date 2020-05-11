package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasInventoryCardId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.CopiesInfo.ANNULLED_COPY;
import static org.gudartem.aars.db.constants.ColumnName.CopiesInfo.COPY_DATE;
import static org.gudartem.aars.db.constants.ColumnName.CopiesInfo.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.CopiesInfo.EMPLOYEE_ID;
import static org.gudartem.aars.db.constants.ColumnName.CopiesInfo.RECEIVED_COPY;
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;
import static org.gudartem.aars.db.constants.TableName.COPIES_INFO;

@Entity
@Table(name = COPIES_INFO)
public class CopiesInfo extends AbstractHasInventoryCardId<UUID> {

    @Column(name = COPY_DATE)
    private OffsetDateTime copyDate;

    @Column(name = RECEIVED_COPY)
    private String receivedCopy;

    @Column(name = ANNULLED_COPY)
    private String annulledCopy;

    private String designation;

    @Column(name = EMPLOYEE_ID, columnDefinition = UUID_TYPE)
    private UUID employeeId;

    public OffsetDateTime getCopyDate() {
        return copyDate;
    }

    public void setCopyDate(OffsetDateTime copyDate) {
        this.copyDate = copyDate;
    }

    public String getReceivedCopy() {
        return receivedCopy;
    }

    public void setReceivedCopy(String receivedCopy) {
        this.receivedCopy = receivedCopy;
    }

    public String getAnnulledCopy() {
        return annulledCopy;
    }

    public void setAnnulledCopy(String annulledCopy) {
        this.annulledCopy = annulledCopy;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
