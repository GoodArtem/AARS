package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Employee.EMPLOYEE_NAME;
import static org.gudartem.aars.db.constants.TableName.EMPLOYEE;

@Entity
@Table(name = EMPLOYEE)
public class Employee extends AbstractHasId<UUID> {

    @Column(name = EMPLOYEE_NAME)
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
