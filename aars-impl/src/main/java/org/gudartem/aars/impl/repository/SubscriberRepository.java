package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.HasThemeIdRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.SUBSCRIBER_REPOSITORY;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.model.PojoFieldNames.HasThemeId.THEME_ID;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.ANNULLED;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.EX_NUMBER;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.SUBSCRIBER_NAME;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.SUBSCRIBE_DATE;
import static org.gudartem.aars.db.jooq.Tables.SUBSCRIBER;

@Repository(SUBSCRIBER_REPOSITORY)
public class SubscriberRepository
        extends BaseRepository<Subscriber, UUID>
        implements HasThemeIdRepository<Subscriber, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, SUBSCRIBER.ID);
        mapping.put(REVISION, SUBSCRIBER.REVISION);
        mapping.put(THEME_ID, SUBSCRIBER.THEME_ID);
        mapping.put(SUBSCRIBER_NAME, SUBSCRIBER.SUBSCRIBER_NAME);
        mapping.put(SUBSCRIBE_DATE, SUBSCRIBER.SUBSCRIBE_DATE);
        mapping.put(EX_NUMBER, SUBSCRIBER.EX_NUMBER);
        mapping.put(DESIGNATION, SUBSCRIBER.DESIGNATION);
        mapping.put(ANNULLED, SUBSCRIBER.ANNULLED);

        tableDescriptor = tableDescriptorBuilder.table(SUBSCRIBER)
                .idField(SUBSCRIBER.ID)
                .entityType(Subscriber.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }

    @Override
    public Collection<Subscriber> getAllByThemeId(UUID themeId) {
        return findAll(SUBSCRIBER.THEME_ID.eq(themeId));
    }
}
