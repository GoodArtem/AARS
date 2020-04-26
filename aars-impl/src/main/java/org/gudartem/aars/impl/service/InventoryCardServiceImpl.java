package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.InventoryCardRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.DirectoryService;
import org.gudartem.aars.api.service.InventoryCardServiceService;
import org.gudartem.aars.db.model.entity.Directory;
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
import static org.gudartem.aars.api.service.ServiceName.DIRECTORY_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.INVENTORY_CARD_SERVICE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.FORMAT_SET;

@Service(INVENTORY_CARD_SERVICE)
public class InventoryCardServiceImpl
        extends CRUDServiceUUIDImpl<InventoryCard>
        implements InventoryCardServiceService {

    private InventoryCardRepository repository;

    private CardToFormatService cardToFormatService;

    private DirectoryService<Directory, UUID> directoryService;

    public InventoryCardServiceImpl(@Qualifier(INVENTORY_CARD_REPOSITORY) InventoryCardRepository repository,
                                    @Qualifier(CARD_TO_FORMAT_SERVICE) CardToFormatService cardToFormatService,
                                    @Qualifier(DIRECTORY_SERVICE) DirectoryService directoryService) {
        this.repository = repository;
        this.cardToFormatService = cardToFormatService;
        this.directoryService = directoryService;
    }

    @Override
    @Transactional
    public InventoryCard create(InventoryCard inventoryCard) {
        inventoryCard.setCardType(getRootDirectory(inventoryCard.getDirectoryId()).getDirectoryType());
        return super.create(inventoryCard);
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
    @Transactional
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

    @Transactional
    private Directory getRootDirectory(UUID parentDirectoryId) {
        List<Directory> pathToTheme = directoryService.getPathToTheme(parentDirectoryId);
        return pathToTheme.get(pathToTheme.size() - 1);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getNextSequenceInventoryNumber(UUID parentDirectoryId) {
        Directory rootDirectory = getRootDirectory(parentDirectoryId);
        return repository.getNextSequenceInventoryNumber(rootDirectory.getDirectoryType());
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType) {
        return repository.getExistsInventoryCard(id, inventoryNumber, inventoryNumberSuf, cardType);
    }
}
