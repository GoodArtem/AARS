package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Employee;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.EMPLOYEE_REPOSITORY;
import static org.gudartem.aars.model.PojoFieldNames.Employee.EMPLOYEE_NAME;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.db.jooq.Tables.EMPLOYEE;

@Repository(EMPLOYEE_REPOSITORY)
public class EmployeeRepository extends BaseRepository<Employee, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, EMPLOYEE.ID);
        mapping.put(REVISION, EMPLOYEE.REVISION);
        mapping.put(EMPLOYEE_NAME, EMPLOYEE.EMPLOYEE_NAME);

        tableDescriptor = tableDescriptorBuilder.table(EMPLOYEE)
                .idField(EMPLOYEE.ID)
                .entityType(Employee.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }
}
