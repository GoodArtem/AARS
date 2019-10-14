package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.Applicability;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.APPLICABILITY_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.APPLICABILITY_SERVICE;

@Service(APPLICABILITY_SERVICE)
public class ApplicabilityServiceImpl extends CRUDServiceImpl<Applicability, UUID> {

    private Repository repository;

    public ApplicabilityServiceImpl(@Qualifier(APPLICABILITY_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<Applicability, UUID> getRepository() {
        return repository;
    }
}
