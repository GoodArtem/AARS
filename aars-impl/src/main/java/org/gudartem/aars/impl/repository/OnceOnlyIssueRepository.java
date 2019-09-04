package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.HasId.ID;
import static org.gudartem.aars.db.constants.ColumnName.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.db.constants.ColumnName.HasRevision.REVISION;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.EX_NUMBER;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.ISSUE_DATE;
import static org.gudartem.aars.db.constants.ColumnName.OnceOnlyIssue.TO_WHOM;
import static org.gudartem.aars.db.jooq.Tables.ONCE_ONLY_ISSUE;

@Repository("onceOnlyIssueRepository")
public class OnceOnlyIssueRepository extends BaseRepository<OnceOnlyIssue, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, ONCE_ONLY_ISSUE.ID);
        mapping.put(INVENTORY_CARD_ID, ONCE_ONLY_ISSUE.INVENTORY_CARD_ID);
        mapping.put(REVISION, ONCE_ONLY_ISSUE.REVISION);
        mapping.put(ISSUE_DATE, ONCE_ONLY_ISSUE.ISSUE_DATE);
        mapping.put(EX_NUMBER, ONCE_ONLY_ISSUE.EX_NUMBER);
        mapping.put(DESIGNATION, ONCE_ONLY_ISSUE.DESIGNATION);
        mapping.put(TO_WHOM, ONCE_ONLY_ISSUE.TO_WHOM);

        tableDescriptor = tableDescriptorBuilder.table(ONCE_ONLY_ISSUE)
                .idField(ONCE_ONLY_ISSUE.ID)
                .entityType(OnceOnlyIssue.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }
}
