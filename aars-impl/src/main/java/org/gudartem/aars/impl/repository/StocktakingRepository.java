package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.STOCKTAKING_REPOSITORY;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.CHANGED_SHEETS;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.CHANGING;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.DATE_CHANGING;
import static org.gudartem.aars.model.PojoFieldNames.Stocktaking.DOC_NUMBER;
import static org.gudartem.aars.db.jooq.Tables.STOCKTAKING;

@Repository(STOCKTAKING_REPOSITORY)
public class StocktakingRepository
        extends BaseRepository<Stocktaking, UUID>
        implements HasInventoryCardIdRepository<Stocktaking, UUID> {

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
    public Collection<Stocktaking> getAllByInvCardId(UUID inventoryCardId) {
        return findAll(STOCKTAKING.INVENTORY_CARD_ID.eq(inventoryCardId));
    }
}
