package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseDto;

import java.util.UUID;

public class EmployeeDto extends BaseDto<UUID> {

    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
