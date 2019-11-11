package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Applicability;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.APPLICABILITY_REPOSITORY;
import static org.gudartem.aars.db.jooq.Tables.APPLICABILITY;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.APPLICABILITY_DATE;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.APP_INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.CIPHER;
import static org.gudartem.aars.model.PojoFieldNames.Applicability.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;

@Repository(APPLICABILITY_REPOSITORY)
public class ApplicabilityRepository
        extends BaseRepository<Applicability, UUID>
        implements HasInventoryCardIdRepository<Applicability, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, APPLICABILITY.ID);
        mapping.put(INVENTORY_CARD_ID, APPLICABILITY.INVENTORY_CARD_ID);
        mapping.put(REVISION, APPLICABILITY.REVISION);
        mapping.put(APPLICABILITY_DATE, APPLICABILITY.APPLICABILITY_DATE);
        mapping.put(DESIGNATION, APPLICABILITY.DESIGNATION);
        mapping.put(CIPHER, APPLICABILITY.CIPHER);
        mapping.put(APP_INVENTORY_CARD_ID, APPLICABILITY.APP_INVENTORY_CARD_ID);

        tableDescriptor = tableDescriptorBuilder.table(APPLICABILITY)
                .idField(APPLICABILITY.ID)
                .entityType(Applicability.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }

    @Override
    public List<Applicability> getAllByInvCardId(UUID inventoryCardId) {
        return findAll(APPLICABILITY.APPLICABILITY_DATE.asc(), APPLICABILITY.INVENTORY_CARD_ID.eq(inventoryCardId));
    }
}
