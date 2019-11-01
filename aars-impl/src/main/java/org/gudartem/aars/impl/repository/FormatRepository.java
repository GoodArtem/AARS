package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Format;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.FORMAT_REPOSITORY;
import static org.gudartem.aars.model.PojoFieldNames.Format.FORMAT_NAME;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.db.jooq.Tables.FORMAT;

@Repository(FORMAT_REPOSITORY)
public class FormatRepository extends BaseRepository<Format, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, FORMAT.ID);
        mapping.put(REVISION, FORMAT.REVISION);
        mapping.put(FORMAT_NAME, FORMAT.FORMAT_NAME);

        tableDescriptor = tableDescriptorBuilder.table(FORMAT)
                .idField(FORMAT.ID)
                .entityType(Format.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }
}
