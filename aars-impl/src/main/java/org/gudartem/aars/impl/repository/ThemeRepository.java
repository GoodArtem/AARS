package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Theme;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.HasId.ID;
import static org.gudartem.aars.db.constants.ColumnName.HasRevision.REVISION;
import static org.gudartem.aars.db.constants.ColumnName.Theme.ARCHIVE_DATE;
import static org.gudartem.aars.db.constants.ColumnName.Theme.CIPHER;
import static org.gudartem.aars.db.constants.ColumnName.Theme.HAS_CHANGES;
import static org.gudartem.aars.db.constants.ColumnName.Theme.THEME_NAME;
import static org.gudartem.aars.db.jooq.Tables.THEME;

@Repository("themeRepository")
public class ThemeRepository extends BaseRepository<Theme, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, THEME.ID);
        mapping.put(REVISION, THEME.REVISION);
        mapping.put(THEME_NAME, THEME.THEME_NAME);
        mapping.put(ARCHIVE_DATE, THEME.ARCHIVE_DATE);
        mapping.put(CIPHER, THEME.CIPHER);
        mapping.put(HAS_CHANGES, THEME.HAS_CHANGES);

        tableDescriptor = tableDescriptorBuilder.table(THEME)
                .idField(THEME.ID)
                .entityType(Theme.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }
}
