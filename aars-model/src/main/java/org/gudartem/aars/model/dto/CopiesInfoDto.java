package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseInventoryCardIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

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
    }

    public Integer getReceivedCopy() {
        return receivedCopy;
    }

    public void setReceivedCopy(Integer receivedCopy) {
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
