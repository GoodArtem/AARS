package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.COPIES_INFO_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.COPIES_INFO_SERVICE;

@Service(COPIES_INFO_SERVICE)
public class CopiesInfoServiceImpl
        extends CRUDServiceUUIDImpl<CopiesInfo>
        implements HasInventoryCardIdService<CopiesInfo, UUID> {

    private HasInventoryCardIdRepository repository;

    public CopiesInfoServiceImpl(@Qualifier(COPIES_INFO_REPOSITORY) HasInventoryCardIdRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CopiesInfo> getAllByInvCardId(UUID inventoryCardId) {
        return repository.getAllByInvCardId(inventoryCardId);
    }

    @Override
    protected HasInventoryCardIdRepository<CopiesInfo, UUID> getRepository() {
        return repository;
    }
}
