package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.entity.InventoryCard;

import java.util.UUID;

public interface InventoryCardRepository extends HasDirectoryIdRepository<InventoryCard, UUID> {
    Integer getNextSequenceInventoryNumber(Integer cardType);

    Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType);
}
