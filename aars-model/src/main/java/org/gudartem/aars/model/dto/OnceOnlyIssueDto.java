package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseInventoryCardIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.EX_NUMBER;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.ISSUE_DATE;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.TO_WHOM;

public class OnceOnlyIssueDto extends BaseInventoryCardIdDto<UUID> {

    private OffsetDateTime issueDate;

    private String exNumber;

    private String designation;

    private String toWhom;

    public OffsetDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(OffsetDateTime issueDate) {
        this.issueDate = issueDate;
        addNullField(ISSUE_DATE, issueDate);
    }

    public String getExNumber() {
        return exNumber;
    }

    public void setExNumber(String exNumber) {
        this.exNumber = exNumber;
        addNullField(EX_NUMBER, exNumber);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
        addNullField(DESIGNATION, designation);
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
        addNullField(TO_WHOM, toWhom);
    }
}
