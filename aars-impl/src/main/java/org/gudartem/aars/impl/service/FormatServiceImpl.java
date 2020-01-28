package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.Format;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.FORMAT_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.FORMAT_SERVICE;

@Service(FORMAT_SERVICE)
public class FormatServiceImpl extends CRUDServiceUUIDImpl<Format> {

    private Repository repository;

    public FormatServiceImpl(@Qualifier(FORMAT_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<Format, UUID> getRepository() {
        return repository;
    }
}
