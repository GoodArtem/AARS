package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasDirectoryIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasDirectoryIdService;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.gudartem.aars.impl.service.internal.CardToFormatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.INVENTORY_CARD_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.CARD_TO_FORMAT_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.INVENTORY_CARD_SERVICE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.FORMAT_SET;

@Service(INVENTORY_CARD_SERVICE)
public class InventoryCardServiceImpl
        extends CRUDServiceUUIDImpl<InventoryCard>
        implements HasDirectoryIdService<InventoryCard, UUID> {

    private HasDirectoryIdRepository repository;

    private CardToFormatService cardToFormatService;

    public InventoryCardServiceImpl(@Qualifier(INVENTORY_CARD_REPOSITORY) HasDirectoryIdRepository repository,
                                    @Qualifier(CARD_TO_FORMAT_SERVICE) CardToFormatService cardToFormatService) {
        this.repository = repository;
        this.cardToFormatService = cardToFormatService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryCard> getAllByThemeId(UUID themeId) {
        List<InventoryCard> result = repository.getAllByThemeId(themeId);
        if (result != null && !result.isEmpty()) {
            for (InventoryCard e : result) {
                postOperationEnrich(e);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryCard> getAllByDirectoryId(UUID directoryId) {
        List<InventoryCard> result = repository.getAllByDirectoryId(directoryId);
        if (result != null && !result.isEmpty()) {
            for (InventoryCard e : result) {
                postOperationEnrich(e);
            }
        }
        return result;
    }

    @Override
    protected Repository<InventoryCard, UUID> getRepository() {
        return repository;
    }

    @Override
    protected InventoryCard postOperationEnrich(InventoryCard entityToEnrich,
                                                InventoryCard baseEntity, Collection<String> fetchPlan) {
        if (baseEntity != null
                && (baseEntity.getFormatSet() != null
                || (baseEntity.getNullFields() != null && baseEntity.getNullFields().contains(FORMAT_SET)))) {
            cardToFormatService.updateFormatSet(baseEntity.getId(), baseEntity.getFormatSet());
        }
        if (fetchPlan == null || fetchPlan.isEmpty() || fetchPlan.contains(FORMAT_SET)) {
            entityToEnrich.setFormatSet(cardToFormatService.getFormatSet(entityToEnrich.getId()));
        }
        return super.postOperationEnrich(entityToEnrich, baseEntity, fetchPlan);
    }
}
