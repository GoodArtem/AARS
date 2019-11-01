package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseDto;

import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Employee.EMPLOYEE_NAME;

public class EmployeeDto extends BaseDto<UUID> {

    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        addNullField(EMPLOYEE_NAME, employeeName);
    }
}
