package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.api.repository.TableDescriptorBuilder;
import org.gudartem.aars.db.model.HasId;
import org.jooq.Field;
import org.jooq.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Scope(SCOPE_PROTOTYPE)
@Component
public class TableDescriptorBuilderImpl implements TableDescriptorBuilder {
    private Table table;
    private Field idField;
    private Field revisionField;
    private Class<? extends HasId> entityType;
    private Map<String, Field> propertyFieldMapping;

    @Override
    public TableDescriptorBuilder table(final Table table) {
        this.table = table;
        return this;
    }

    @Override
    public TableDescriptorBuilder idField(final Field idField) {
        this.idField = idField;
        return this;
    }

    @Override
    public TableDescriptorBuilder entityType(final Class<? extends HasId> entityType) {
        this.entityType = entityType;
        return this;
    }

    @Override
    public TableDescriptorBuilder propertyFieldMapping(final Map<String, Field> propertyFieldMapping) {
        this.propertyFieldMapping = propertyFieldMapping;
        return this;
    }

    @Override
    public TableDescriptor build() {
        return new TableDescriptor(table, idField, propertyFieldMapping, entityType, revisionField);
    }

    public TableDescriptorBuilder revisionField(final Field revisionField) {
        this.revisionField = revisionField;
        return this;
    }
}
