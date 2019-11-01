package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.COPIES_INFO_REPOSITORY;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.ANNULLED_COPY;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.COPY_DATE;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.EMPLOYEE_ID;
import static org.gudartem.aars.model.PojoFieldNames.CopiesInfo.RECEIVED_COPY;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.db.jooq.Tables.COPIES_INFO;

@Repository(COPIES_INFO_REPOSITORY)
public class CopiesInfoRepository
        extends BaseRepository<CopiesInfo, UUID>
        implements HasInventoryCardIdRepository<CopiesInfo, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, COPIES_INFO.ID);
        mapping.put(INVENTORY_CARD_ID, COPIES_INFO.INVENTORY_CARD_ID);
        mapping.put(REVISION, COPIES_INFO.REVISION);
        mapping.put(COPY_DATE, COPIES_INFO.COPY_DATE);
        mapping.put(RECEIVED_COPY, COPIES_INFO.RECEIVED_COPY);
        mapping.put(ANNULLED_COPY, COPIES_INFO.ANNULLED_COPY);
        mapping.put(DESIGNATION, COPIES_INFO.DESIGNATION);
        mapping.put(EMPLOYEE_ID, COPIES_INFO.EMPLOYEE_ID);

        tableDescriptor = tableDescriptorBuilder.table(COPIES_INFO)
                .idField(COPIES_INFO.ID)
                .entityType(CopiesInfo.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }

    @Override
    public Collection<CopiesInfo> getAllByInvCardId(UUID inventoryCardId) {
        return findAll(COPIES_INFO.INVENTORY_CARD_ID.eq(inventoryCardId));
    }
}
