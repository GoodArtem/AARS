package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.Applicability;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.APPLICABILITY_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.APPLICABILITY_SERVICE;

@Service(APPLICABILITY_SERVICE)
public class ApplicabilityServiceImpl
        extends CRUDServiceUUIDImpl<Applicability>
        implements HasInventoryCardIdService<Applicability, UUID> {

    private HasInventoryCardIdRepository repository;

    public ApplicabilityServiceImpl(@Qualifier(APPLICABILITY_REPOSITORY) HasInventoryCardIdRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Applicability> getAllByInvCardId(UUID inventoryCardId) {
        return repository.getAllByInvCardId(inventoryCardId);
    }

    @Override
    protected Repository<Applicability, UUID> getRepository() {
        return repository;
    }
}
