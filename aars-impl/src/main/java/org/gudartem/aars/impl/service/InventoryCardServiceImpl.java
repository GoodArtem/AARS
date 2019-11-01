package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasThemeIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasThemeIdService;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.INVENTORY_CARD_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.INVENTORY_CARD_SERVICE;

@Service(INVENTORY_CARD_SERVICE)
public class InventoryCardServiceImpl extends CRUDServiceImpl<InventoryCard, UUID> implements HasThemeIdService<InventoryCard, UUID> {

    private HasThemeIdRepository repository;

    public InventoryCardServiceImpl(@Qualifier(INVENTORY_CARD_REPOSITORY) HasThemeIdRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<InventoryCard> getAllByThemeId(UUID themeId) {
        return repository.getAllByThemeId(themeId);
    }

    @Override
    protected Repository<InventoryCard, UUID> getRepository() {
        return repository;
    }
}
