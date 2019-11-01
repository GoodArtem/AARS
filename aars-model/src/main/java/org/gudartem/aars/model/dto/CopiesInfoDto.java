package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseInventoryCardIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.ANNULLED_COPY;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.COPY_DATE;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.EMPLOYEE_ID;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.RECEIVED_COPY;

public class CopiesInfoDto extends BaseInventoryCardIdDto<UUID> {

    private OffsetDateTime copyDate;

    private Integer receivedCopy;

    private String annulledCopy;

    private String designation;

    private UUID employeeId;

    public OffsetDateTime getCopyDate() {
        return copyDate;
    }

    public void setCopyDate(OffsetDateTime copyDate) {
        this.copyDate = copyDate;
        addNullField(COPY_DATE, copyDate);
    }

    public Integer getReceivedCopy() {
        return receivedCopy;
    }

    public void setReceivedCopy(Integer receivedCopy) {
        this.receivedCopy = receivedCopy;
        addNullField(RECEIVED_COPY, receivedCopy);
    }

    public String getAnnulledCopy() {
        return annulledCopy;
    }

    public void setAnnulledCopy(String annulledCopy) {
        this.annulledCopy = annulledCopy;
        addNullField(ANNULLED_COPY, annulledCopy);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
        addNullField(DESIGNATION, designation);
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
        addNullField(EMPLOYEE_ID, employeeId);
    }
}
