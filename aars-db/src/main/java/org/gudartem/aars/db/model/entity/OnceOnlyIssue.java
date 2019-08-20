package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasInventoryCardId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.EX_NUMBER;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.ISSUE_DATE;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.TO_WHOM;
import static org.gudartem.aars.db.constants.TableName.ONCE_ONLY_ISSUE;

@Entity
@Table(name = ONCE_ONLY_ISSUE)
public class OnceOnlyIssue extends AbstractHasInventoryCardId<UUID> {

    @Column(name = ISSUE_DATE)
    private OffsetDateTime issueDate;

    @Column(name = EX_NUMBER)
    private String exNumber;

    @Column(name = DESIGNATION)
    private String designation;

    @Column(name = TO_WHOM)
    private String toWhom;

    public OffsetDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(OffsetDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public String getExNumber() {
        return exNumber;
    }

    public void setExNumber(String exNumber) {
        this.exNumber = exNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }
}
