package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.HasId.ID;
import static org.gudartem.aars.db.constants.ColumnName.HasRevision.REVISION;
import static org.gudartem.aars.db.constants.ColumnName.HasThemeId.THEME_ID;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.ANNULLED;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.EX_NUMBER;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.SUBSCRIBER_NAME;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.SUBSCRIBE_DATE;
import static org.gudartem.aars.db.jooq.Tables.SUBSCRIBER;

@Repository("subscriberRepository")
public class SubscriberRepository extends BaseRepository<Subscriber, UUID> {

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
}
