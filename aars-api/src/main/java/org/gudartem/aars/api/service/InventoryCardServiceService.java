package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.entity.InventoryCard;

import java.util.UUID;

public interface InventoryCardServiceService extends HasDirectoryIdService<InventoryCard, UUID> {
    Integer getNextSequenceInventoryNumber(UUID parentDirectoryId);

    Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType);
}
