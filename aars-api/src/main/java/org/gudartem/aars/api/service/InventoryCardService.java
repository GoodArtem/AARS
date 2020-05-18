package org.gudartem.aars.api.service;

import org.gudartem.aars.api.model.TreeWithOpenedBranchResult;
import org.gudartem.aars.db.model.entity.InventoryCard;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface InventoryCardService extends HasDirectoryIdService<InventoryCard, UUID> {
    Integer getNextSequenceInventoryNumber(UUID parentDirectoryId);

    Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType);

    Collection<UUID> getPathToInventoryCard(UUID inventoryCardId);

    TreeWithOpenedBranchResult getTreeWithOpenedBranch(UUID inventoryCardId);

    List<InventoryCard> getAllByThemeIdWithLastChange(UUID themeId, Integer cardType);

    List<InventoryCard> getAllByDirectoryIdWithLastChange(UUID directoryId);

    Collection<InventoryCard> getInventoryCardBySearchString(String searchString);
}
