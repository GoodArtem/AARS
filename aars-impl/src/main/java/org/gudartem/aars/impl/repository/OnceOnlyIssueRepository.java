package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.ONCE_ONLY_ISSUE_REPOSITORY;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.EX_NUMBER;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.ISSUE_DATE;
import static org.gudartem.aars.model.PojoFieldNames.OnceOnlyIssue.TO_WHOM;
import static org.gudartem.aars.db.jooq.Tables.ONCE_ONLY_ISSUE;

@Repository(ONCE_ONLY_ISSUE_REPOSITORY)
public class OnceOnlyIssueRepository
        extends BaseRepository<OnceOnlyIssue, UUID>
        implements HasInventoryCardIdRepository<OnceOnlyIssue, UUID> {

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

    @Override
    public Collection<OnceOnlyIssue> getAllByInvCardId(UUID inventoryCardId) {
        return findAll(ONCE_ONLY_ISSUE.INVENTORY_CARD_ID.eq(inventoryCardId));
    }
}
