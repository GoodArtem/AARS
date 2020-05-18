package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.constants.BusinessConstants;
import org.gudartem.aars.api.repository.StocktakingRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.jooq.Condition;
import org.jooq.Field;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.STOCKTAKING_REPOSITORY;
import static org.gudartem.aars.db.jooq.Tables.STOCKTAKING;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.CHANGED_SHEETS;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.CHANGING;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.DATE_CHANGING;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.DOC_NUMBER;

@Repository(STOCKTAKING_REPOSITORY)
public class StocktakingRepositoryImpl
        extends BaseRepository<Stocktaking, UUID>
        implements StocktakingRepository {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, STOCKTAKING.ID);
        mapping.put(INVENTORY_CARD_ID, STOCKTAKING.INVENTORY_CARD_ID);
        mapping.put(REVISION, STOCKTAKING.REVISION);
        mapping.put(CHANGING, STOCKTAKING.CHANGING);
        mapping.put(DOC_NUMBER, STOCKTAKING.DOC_NUMBER);
        mapping.put(DATE_CHANGING, STOCKTAKING.DATE_CHANGING);
        mapping.put(CHANGED_SHEETS, STOCKTAKING.CHANGED_SHEETS);

        tableDescriptor = tableDescriptorBuilder.table(STOCKTAKING)
                .idField(STOCKTAKING.ID)
                .entityType(Stocktaking.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }

    @Override
    @Transactional
    public List<Stocktaking> getAllByInvCardId(UUID inventoryCardId) {
        return findAll(STOCKTAKING.DATE_CHANGING.asc(), STOCKTAKING.INVENTORY_CARD_ID.eq(inventoryCardId));
    }

    @Override
    @Transactional
    public boolean newStocktakingAlreadyExists(UUID stocktakingId, UUID inventoryCardId) {
        TableDescriptor descriptor = getTableDescriptor();
        Condition condition = (stocktakingId == null ? STOCKTAKING.ID.isNotNull() : STOCKTAKING.ID.ne(stocktakingId))
                .and(STOCKTAKING.INVENTORY_CARD_ID.eq(inventoryCardId))
                .and(STOCKTAKING.CHANGED_SHEETS.equalIgnoreCase(BusinessConstants.NEW_STOCKTAKING));

        int count = getContext().fetchCount(descriptor.getTable(), condition);
        return count > 0;
    }

    @Override
    @Transactional
    public boolean newStocktakingHasIncorrectDate(UUID stocktakingId, UUID inventoryCardId, OffsetDateTime date) {
        TableDescriptor descriptor = getTableDescriptor();
        Condition condition = (stocktakingId == null ? STOCKTAKING.ID.isNotNull() : STOCKTAKING.ID.ne(stocktakingId))
                .and(STOCKTAKING.INVENTORY_CARD_ID.eq(inventoryCardId))
                .and(STOCKTAKING.DATE_CHANGING.le(date));

        int count = getContext().fetchCount(descriptor.getTable(), condition);
        return count > 0;
    }
}
