package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Table;

import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

public class TableDescriptor {
    private static final String NO_REVISION_FIELD_FOUND = "No revision field found for table: %s";
    private static final String TABLE_MUST_NOT_BE_BLANK = "Table must not be blank";
    private static final String PRIMARY_KEY_IS_NOT_PROVIDED = "No primary key provided for table: %s";
    private static final String PROPERTY_FIELD_MAPPING_IS_NOT_PROVIDED =
            "Property field mapping is not provided for table: %s";
    private static final String ENTITY_TYPE_IS_NOT_PROVIDED = "Entity type is not provided for table: %s";

    private Table table;
    private Field idField;
    private Field revisionField;
    private Class<? extends HasId> entityType;
    private Map<String, Field> propertyFieldMapping;

    public TableDescriptor(
            Table table,
            Field idField,
            Field pkField,
            Map<String, Field> propertyFieldMapping,
            Class<? extends HasId> entityType,
            Set<String> compositeFields
    ) {
        this(table, idField, propertyFieldMapping, entityType, null);
    }

    public TableDescriptor(
            Table table,
            Field idField,
            Map<String, Field> propertyFieldMapping,
            Class<? extends HasId> entityType,
            Field revisionField
    ) {
        notNull(table, TABLE_MUST_NOT_BE_BLANK);
        Name unqualifiedName = table.getUnqualifiedName();
        notNull(idField, format(PRIMARY_KEY_IS_NOT_PROVIDED, unqualifiedName));
        notNull(entityType, ENTITY_TYPE_IS_NOT_PROVIDED);
        notEmpty(propertyFieldMapping, format(PROPERTY_FIELD_MAPPING_IS_NOT_PROVIDED, unqualifiedName));

        this.revisionField = revisionField;
        this.table = table;
        this.idField = idField;
        this.propertyFieldMapping = propertyFieldMapping;
        this.entityType = entityType;
    }

    public Table getTable() {
        return table;
    }

    public Field getIdField() {
        return idField;
    }

    public Map<String, Field> getPropertyFieldMapping() {
        return propertyFieldMapping;
    }

    public Class<? extends HasId> getEntityType() {
        return entityType;
    }

    public Field getRevisionField() {
        if (revisionField == null) {
            throw new UnsupportedOperationException(format(NO_REVISION_FIELD_FOUND, getTable().getUnqualifiedName()));
        }

        return revisionField;
    }
}
