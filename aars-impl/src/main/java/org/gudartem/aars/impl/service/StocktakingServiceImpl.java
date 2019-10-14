package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.STOCKTAKING_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.STOCKTAKING_SERVICE;

@Service(STOCKTAKING_SERVICE)
public class StocktakingServiceImpl extends CRUDServiceImpl<Stocktaking, UUID> {

    private Repository repository;

    public StocktakingServiceImpl(@Qualifier(STOCKTAKING_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<Stocktaking, UUID> getRepository() {
        return repository;
    }
}
