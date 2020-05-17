package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.entity.Stocktaking;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface StocktakingRepository extends HasInventoryCardIdRepository<Stocktaking, UUID> {
    boolean newStocktakingAlreadyExists(UUID stocktakingId, UUID inventoryCardId);

    boolean newStocktakingHasIncorrectDate(UUID stocktakingId, UUID inventoryCardId, OffsetDateTime date);
}
