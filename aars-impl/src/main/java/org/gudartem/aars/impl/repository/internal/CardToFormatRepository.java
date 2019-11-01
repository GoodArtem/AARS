package org.gudartem.aars.impl.repository.internal;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.CARD_TO_FORMAT_REPOSITORY;
import static org.gudartem.aars.db.jooq.Tables.CARD_TO_FORMAT;

@Repository(CARD_TO_FORMAT_REPOSITORY)
public class CardToFormatRepository {

    @Autowired
    @Qualifier("aarsDslContext")
    private DSLContext context;

    public Collection<UUID> getFormatIds(UUID inventoryCardId) {
        return context.selectFrom(CARD_TO_FORMAT)
                .where(CARD_TO_FORMAT.INVENTORY_CARD_ID.eq(inventoryCardId))
                .fetch(CARD_TO_FORMAT.FORMAT_ID);
    }

    public void updateRelations(UUID inventoryCardId, Set<UUID> formatIds) {
        if (formatIds == null || formatIds.isEmpty()) {
            deleteRelations(inventoryCardId);
            return;
        }

        Collection<UUID> existingFormatIds = getFormatIds(inventoryCardId);
        boolean existingFormatIdsNotEmpty = existingFormatIds != null && !existingFormatIds.isEmpty();
        for (UUID formatId : formatIds) {
            if (existingFormatIdsNotEmpty && existingFormatIds.contains(formatId)) {
                existingFormatIds.remove(formatId);
            } else {
                insertRelation(inventoryCardId, formatId);
            }
        }

        if (!existingFormatIdsNotEmpty) {
            for (UUID formatId : existingFormatIds) {
                deleteRelations(inventoryCardId, formatId);
            }
        }
    }

    private void insertRelation(UUID inventoryCardId, UUID formatId) {
        context.insertInto(CARD_TO_FORMAT, CARD_TO_FORMAT.INVENTORY_CARD_ID, CARD_TO_FORMAT.FORMAT_ID)
                .values(inventoryCardId, formatId)
                .execute();
    }

    private void deleteRelations(UUID inventoryCardId) {
        context.deleteFrom(CARD_TO_FORMAT)
                .where(CARD_TO_FORMAT.INVENTORY_CARD_ID.eq(inventoryCardId))
                .execute();
    }

    private void deleteRelations(UUID inventoryCardId, UUID formatId) {
        context.deleteFrom(CARD_TO_FORMAT)
                .where(CARD_TO_FORMAT.INVENTORY_CARD_ID.eq(inventoryCardId),
                        CARD_TO_FORMAT.FORMAT_ID.eq(formatId))
                .execute();
    }
}
