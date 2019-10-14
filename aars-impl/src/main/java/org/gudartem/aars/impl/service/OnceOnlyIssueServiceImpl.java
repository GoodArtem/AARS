package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.ONCE_ONLY_ISSUE_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.ONCE_ONLY_ISSUE_SERVICE;

@Service(ONCE_ONLY_ISSUE_SERVICE)
public class OnceOnlyIssueServiceImpl extends CRUDServiceImpl<OnceOnlyIssue, UUID> {

    private Repository repository;

    public OnceOnlyIssueServiceImpl(@Qualifier(ONCE_ONLY_ISSUE_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<OnceOnlyIssue, UUID> getRepository() {
        return repository;
    }
}
