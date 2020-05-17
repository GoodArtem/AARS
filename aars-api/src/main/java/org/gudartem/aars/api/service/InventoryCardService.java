package org.gudartem.aars.api.service;

import org.gudartem.aars.api.model.TreeWithOpenedBranchResult;
import org.gudartem.aars.db.model.entity.InventoryCard;

import java.util.Collection;
import java.util.UUID;

public interface InventoryCardService extends HasDirectoryIdService<InventoryCard, UUID> {
    Integer getNextSequenceInventoryNumber(UUID parentDirectoryId);

    Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType);

    Collection<UUID> getPathToInventoryCard(UUID inventoryCardId);

    TreeWithOpenedBranchResult getTreeWithOpenedBranch(UUID inventoryCardId);
}
