package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Applicability;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Applicability.APPLICABILITY_DATE;
import static org.gudartem.aars.db.constants.ColumnName.Applicability.APP_INVENTORY_CARD_ID;
import static org.gudartem.aars.db.constants.ColumnName.Applicability.CIPHER;
import static org.gudartem.aars.db.constants.ColumnName.Applicability.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.HasId.ID;
import static org.gudartem.aars.db.constants.ColumnName.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.db.constants.ColumnName.HasRevision.REVISION;
import static org.gudartem.aars.db.jooq.Tables.APPLICABILITY;

@Repository("applicabilityRepository")
public class ApplicabilityRepository extends BaseRepository<Applicability, UUID> {

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
}
