package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.entity.InventoryCard;

import java.util.List;
import java.util.UUID;

public interface InventoryCardRepository extends HasDirectoryIdRepository<InventoryCard, UUID> {
    List<InventoryCard> getAllByThemeId(UUID themeId, Integer cardType);

    Integer getNextSequenceInventoryNumber(Integer cardType);

    Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType);

    List<InventoryCard> getInventoryCardBySearchString(String searchString);
}
