package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.gudartem.aars.db.model.entity.Employee;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.COPIES_INFO_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.COPIES_INFO_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.EMPLOYEE_SERVICE;

@Service(COPIES_INFO_SERVICE)
public class CopiesInfoServiceImpl
        extends CRUDServiceUUIDImpl<CopiesInfo>
        implements HasInventoryCardIdService<CopiesInfo, UUID> {

    private final HasInventoryCardIdRepository repository;

    private final CRUDService<Employee, UUID> employeeService;

    public CopiesInfoServiceImpl(@Qualifier(COPIES_INFO_REPOSITORY) HasInventoryCardIdRepository repository,
                                 @Qualifier(EMPLOYEE_SERVICE) CRUDService<Employee, UUID> employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CopiesInfo> getAllByInvCardId(UUID inventoryCardId) {
        List<CopiesInfo> result = repository.getAllByInvCardId(inventoryCardId);
        return postOperationEnrich(result);
    }

    @Override
    @Transactional
    protected CopiesInfo postOperationEnrich(CopiesInfo entityToEnrich) {
        Employee employee = employeeService.getById(entityToEnrich.getEmployeeId());
        entityToEnrich.setDesignation(employee.getEmployeeName());
        return super.postOperationEnrich(entityToEnrich);
    }

    @Override
    protected HasInventoryCardIdRepository<CopiesInfo, UUID> getRepository() {
        return repository;
    }
}
