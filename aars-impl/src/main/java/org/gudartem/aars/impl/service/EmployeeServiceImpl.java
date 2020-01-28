package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.Employee;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.EMPLOYEE_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.EMPLOYEE_SERVICE;

@Service(EMPLOYEE_SERVICE)
public class EmployeeServiceImpl extends CRUDServiceUUIDImpl<Employee> {

    private Repository repository;

    public EmployeeServiceImpl(@Qualifier(EMPLOYEE_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<Employee, UUID> getRepository() {
        return repository;
    }
}
