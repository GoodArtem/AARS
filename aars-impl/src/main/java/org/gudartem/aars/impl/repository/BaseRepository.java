package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.api.repository.TableDescriptorBuilder;
import org.gudartem.aars.db.model.HasId;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

public abstract class BaseRepository<T extends HasId<ID>, ID extends Serializable> implements Repository<T, ID> {

    @Autowired
    protected TableDescriptorBuilder tableDescriptorBuilder;

    @Autowired
    @Qualifier("aarsDslContext")
    private DSLContext context;

    public abstract TableDescriptor getTableDescriptor();

    @Override
    public T findById(final ID id) {
        TableDescriptor descriptor = getTableDescriptor();
        Record fetch = context.selectFrom(descriptor.getTable()).where(descriptor.getIdField().eq(id)).fetchOne();
        return (T) valueOrNull(fetch, (Class<T>) descriptor.getEntityType());
    }

    @Override
    public void deleteById(final ID id) {
        TableDescriptor descriptor = getTableDescriptor();
        context.deleteFrom(descriptor.getTable()).where(descriptor.getIdField().eq(id)).execute();
    }

    @Override
    public boolean existsById(final ID id) {
        TableDescriptor descriptor = getTableDescriptor();
        return context.fetchExists(
                context.selectOne()
                        .from(descriptor.getTable())
                        .where(descriptor.getIdField().eq(id))
        );
    }

    @Override
    public <S extends T> S insert(S entity) {
        UpdatableRecord record = context.<UpdatableRecord>newRecord(getTableDescriptor().getTable());
        record.from(entity);
        preInsert(entity, record);
        record.insert();
        return postInsert(record.into(entity));
    }

    @Override
    public <S extends T> S update(S entity) {
        UpdatableRecord record = context.<UpdatableRecord>newRecord(getTableDescriptor().getTable());
        record.from(entity);
        preUpdate(entity, record);
        record.update();
        return postUpdate(record.into(entity));
    }

    @Override
    public Collection<T> findAll() {
        TableDescriptor descriptor = getTableDescriptor();
        return (Collection<T>) context.selectFrom(descriptor.getTable()).fetchInto(descriptor.getEntityType());
    }

    @Override
    public Collection<T> findAll(Collection<? extends Condition> conditions) {
        TableDescriptor descriptor = getTableDescriptor();
        return (Collection<T>) context.selectFrom(descriptor.getTable())
                .where(conditions)
                .fetchInto(descriptor.getEntityType());
    }

    public Collection<T> findAll(Condition...conditions) {
        TableDescriptor descriptor = getTableDescriptor();
        return (Collection<T>) context.selectFrom(descriptor.getTable())
                .where(conditions)
                .fetchInto(descriptor.getEntityType());
    }

    protected DSLContext getContext() {
        return context;
    }

    protected <S extends T> void preInsert(S entity, UpdatableRecord record) {
    }

    protected <S extends T> S postInsert(S entity) {
        return entity;
    }

    protected <S extends T> void preUpdate(S entity, UpdatableRecord record) {
        if (entity.getNullFields() != null && !entity.getNullFields().isEmpty()) {
            for (Field<?> field : record.fields()) {
                if (isNotManuallySetToNull(field, record, propertyNamesToField(entity.getNullFields()))) {
                    record.changed(field, false);
                }
            }
        }
    }

    protected <S extends T> S postUpdate(S entity) {
        return entity;
    }

    private Collection<String> propertyNamesToField(final Collection<String> properties) {
        Map<String, Field> mapping = getTableDescriptor().getPropertyFieldMapping();
        return properties.stream().peek(f -> {
            if (!mapping.containsKey(f)) {
                // TODO: create exception for unprocessed fields
            }
        }).map(property -> mapping.get(property).getUnqualifiedName().toString()).collect(toSet());
    }

    private boolean isNotManuallySetToNull(final Field field, final Record record, final Collection<String> nullFields) {
        return field.getValue(record) == null && !nullFields.contains(field.getUnqualifiedName().toString());
    }

    private T valueOrNull(Record record, Class<T> type) {
        return record == null ? null : record.into(type);
    }
}
