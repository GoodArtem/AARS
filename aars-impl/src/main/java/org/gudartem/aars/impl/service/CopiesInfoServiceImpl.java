package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.COPIES_INFO_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.COPIES_INFO_SERVICE;

@Service(COPIES_INFO_SERVICE)
public class CopiesInfoServiceImpl extends CRUDServiceImpl<CopiesInfo, UUID> {

    private Repository repository;

    public CopiesInfoServiceImpl(@Qualifier(COPIES_INFO_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<CopiesInfo, UUID> getRepository() {
        return repository;
    }
}
