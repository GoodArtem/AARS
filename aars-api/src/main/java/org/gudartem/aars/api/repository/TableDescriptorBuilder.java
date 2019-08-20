package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;
import org.jooq.Field;
import org.jooq.Table;

import java.util.Map;

public interface TableDescriptorBuilder {
    TableDescriptorBuilder table(final Table table);

    TableDescriptorBuilder idField(final Field idField);

    TableDescriptorBuilder entityType(final Class<? extends HasId> entityType);

    TableDescriptorBuilder propertyFieldMapping(final Map<String, Field> propertyFieldMapping);

    TableDescriptor build();
}
