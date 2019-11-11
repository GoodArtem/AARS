package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Employee;
import org.gudartem.aars.model.dto.EmployeeDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface EmployeeMapper extends EntityMapper<Employee, EmployeeDto> {
}
