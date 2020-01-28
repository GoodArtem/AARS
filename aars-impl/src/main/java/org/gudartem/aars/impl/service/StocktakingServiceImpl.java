package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.STOCKTAKING_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.STOCKTAKING_SERVICE;

@Service(STOCKTAKING_SERVICE)
public class StocktakingServiceImpl
        extends CRUDServiceUUIDImpl<Stocktaking>
        implements HasInventoryCardIdService<Stocktaking, UUID> {

    private HasInventoryCardIdRepository repository;

    public StocktakingServiceImpl(@Qualifier(STOCKTAKING_REPOSITORY) HasInventoryCardIdRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Stocktaking> getAllByInvCardId(UUID inventoryCardId) {
        return repository.getAllByInvCardId(inventoryCardId);
    }

    @Override
    protected Repository<Stocktaking, UUID> getRepository() {
        return repository;
    }
}
