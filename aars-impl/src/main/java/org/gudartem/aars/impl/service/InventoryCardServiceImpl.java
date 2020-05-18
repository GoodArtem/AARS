package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.error.model.ErrorCode;
import org.gudartem.aars.api.error.utils.ErrorUtils;
import org.gudartem.aars.api.model.TreeWithOpenedBranchResult;
import org.gudartem.aars.api.repository.InventoryCardRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.api.service.DirectoryService;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.api.service.InventoryCardService;
import org.gudartem.aars.db.model.entity.Directory;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.db.model.entity.Theme;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.gudartem.aars.impl.service.internal.CardToFormatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.INVENTORY_CARD_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.CARD_TO_FORMAT_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.DIRECTORY_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.INVENTORY_CARD_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.STOCKTAKING_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.THEME_SERVICE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.FORMAT_SET;

@Service(INVENTORY_CARD_SERVICE)
public class InventoryCardServiceImpl
        extends CRUDServiceUUIDImpl<InventoryCard>
        implements InventoryCardService {

    private InventoryCardRepository repository;

    private CardToFormatService cardToFormatService;

    private DirectoryService<Directory, UUID> directoryService;

    private CRUDService<Theme, UUID> themeService;

    private HasInventoryCardIdService<Stocktaking, UUID> stocktakingService;

    public InventoryCardServiceImpl(@Qualifier(INVENTORY_CARD_REPOSITORY) InventoryCardRepository repository,
                                    @Qualifier(CARD_TO_FORMAT_SERVICE) CardToFormatService cardToFormatService,
                                    @Qualifier(DIRECTORY_SERVICE) DirectoryService directoryService,
                                    @Qualifier(THEME_SERVICE) CRUDService<Theme, UUID> themeService,
                                    @Qualifier(STOCKTAKING_SERVICE) HasInventoryCardIdService<Stocktaking, UUID> stocktakingService) {
        this.repository = repository;
        this.cardToFormatService = cardToFormatService;
        this.directoryService = directoryService;
        this.themeService = themeService;
        this.stocktakingService = stocktakingService;
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
    public List<InventoryCard> getAllByThemeIdWithLastChange(UUID themeId, Integer cardType) {
        List<InventoryCard> result = repository.getAllByThemeId(themeId, cardType);
        if (result != null && !result.isEmpty()) {
            for (InventoryCard e : result) {
                postOperationEnrich(e);
                List<Stocktaking> stocktakingList = stocktakingService.getAllByInvCardId(e.getId());
                if (stocktakingList != null && !stocktakingList.isEmpty()) {
                    Stocktaking lastChange = stocktakingList.get(stocktakingList.size() - 1);
                    e.setLastChanging(lastChange.getChanging());
                    e.setDateLastChange(lastChange.getDateChanging());
                }
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

    @Transactional(readOnly = true)
    public List<InventoryCard> getAllByDirectoryIdWithLastChange(UUID directoryId) {
        List<InventoryCard> result = repository.getAllByDirectoryId(directoryId);
        if (result != null && !result.isEmpty()) {
            for (InventoryCard e : result) {
                postOperationEnrich(e);
                List<Stocktaking> stocktakingList = stocktakingService.getAllByInvCardId(e.getId());
                if (stocktakingList != null && !stocktakingList.isEmpty()) {
                    Stocktaking lastChange = stocktakingList.get(stocktakingList.size() - 1);
                    e.setLastChanging(lastChange.getChanging());
                    e.setDateLastChange(lastChange.getDateChanging());
                }
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
    protected InventoryCard preCreate(InventoryCard creatingEntity) {
        creatingEntity.setCardType(getRootDirectory(creatingEntity.getDirectoryId()).getDirectoryType());
        if (getExistsInventoryCard(null,
                creatingEntity.getInventoryNumber(),
                creatingEntity.getInventoryNumberSuf(),
                creatingEntity.getCardType())) {
            throw ErrorUtils.getUnprocessableEntityException(ErrorCode.AARS_0002,
                    creatingEntity.getInventoryNumber(), creatingEntity.getInventoryNumberSuf());
        }
        return super.preCreate(creatingEntity);
    }

    @Override
    @Transactional
    protected InventoryCard postOperationEnrich(InventoryCard entityToEnrich, Collection<String> fetchPlan) {
        if (fetchPlan == null || fetchPlan.isEmpty() || fetchPlan.contains(FORMAT_SET)) {
            entityToEnrich.setFormatSet(cardToFormatService.getFormatSet(entityToEnrich.getId()));
        }
        return super.postOperationEnrich(entityToEnrich, fetchPlan);
    }

    @Override
    @Transactional
    protected InventoryCard postCreateOrUpdate(InventoryCard resultEntity, InventoryCard baseEntity) {
        if (baseEntity != null
                && (baseEntity.getFormatSet() != null
                || (baseEntity.getNullFields() != null && baseEntity.getNullFields().contains(FORMAT_SET)))) {
            cardToFormatService.updateFormatSet(baseEntity.getId(), baseEntity.getFormatSet());
        }
        return super.postCreateOrUpdate(resultEntity, baseEntity);
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

    @Override
    @Transactional
    public Collection<UUID> getPathToInventoryCard(UUID inventoryCardId) {
        InventoryCard inventoryCard = getById(inventoryCardId);
        List<Directory> pathToTheme = directoryService.getPathToTheme(inventoryCard.getDirectoryId());
        List<UUID> path = new ArrayList<>();
        for (int i = pathToTheme.size() - 1; i >= 0; i--) {
            path.add(pathToTheme.get(i).getId());
        }
        path.add(inventoryCard.getThemeId());
        return path;
    }

    @Override
    @Transactional
    public TreeWithOpenedBranchResult getTreeWithOpenedBranch(UUID inventoryCardId) {
        Collection<Theme> themeList = themeService.getAll();
        if (themeList == null) {
            return null;
        }
        TreeWithOpenedBranchResult result = new TreeWithOpenedBranchResult(themeList, inventoryCardId);
        InventoryCard inventoryCard = getById(inventoryCardId);
        result.getOpen().add(inventoryCard.getThemeId());
        List<Directory> pathToTheme = directoryService.getPathToTheme(inventoryCard.getDirectoryId());
        for (Theme theme : themeList) {
            if (theme.getId().equals(inventoryCard.getThemeId())) {
                theme.setChildren(directoryService.getAllByThemeId(theme.getId()));
                unrollChildren(theme.getChildren(),
                        pathToTheme.listIterator(pathToTheme.size()),
                        result);
                break;
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<InventoryCard> getInventoryCardBySearchString(String searchString) {
        List<InventoryCard> result = repository.getInventoryCardBySearchString(searchString);
        if (result != null && !result.isEmpty()) {
            for (InventoryCard e : result) {
                postOperationEnrich(e);
            }
        }
        return result;
    }

    @Transactional
    private void unrollChildren(List<Directory> parentDirectories,
                                ListIterator<Directory> iterator,
                                TreeWithOpenedBranchResult result) {
        if (!iterator.hasPrevious() || parentDirectories == null || parentDirectories.isEmpty()) {
            return;
        }
        Directory parent = null;
        UUID parentId = iterator.previous().getId();
        result.getOpen().add(parentId);
        for (Directory directory : parentDirectories) {
            if (directory.getId().equals(parentId)) {
                parent = directory;
                break;
            }
        }
        List<Directory> childDirectoryList = directoryService.getAllByDirectoryId(parent.getId());
        if (childDirectoryList != null) {
            parent.setChildDirectoryList(childDirectoryList);
            unrollChildren(childDirectoryList, iterator, result);
        }
        List<InventoryCard> childInventoryCardList = getAllByDirectoryId(parent.getId());
        if (childInventoryCardList != null) {
            parent.setChildInventoryCardList(childInventoryCardList);
        }

    }
}
