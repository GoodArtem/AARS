package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.ONCE_ONLY_ISSUE_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.ONCE_ONLY_ISSUE_SERVICE;

@Service(ONCE_ONLY_ISSUE_SERVICE)
public class OnceOnlyIssueServiceImpl
        extends CRUDServiceUUIDImpl<OnceOnlyIssue>
        implements HasInventoryCardIdService<OnceOnlyIssue, UUID> {

    private HasInventoryCardIdRepository repository;

    public OnceOnlyIssueServiceImpl(@Qualifier(ONCE_ONLY_ISSUE_REPOSITORY) HasInventoryCardIdRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OnceOnlyIssue> getAllByInvCardId(UUID inventoryCardId) {
        return repository.getAllByInvCardId(inventoryCardId);
    }

    @Override
    protected Repository<OnceOnlyIssue, UUID> getRepository() {
        return repository;
    }
}
