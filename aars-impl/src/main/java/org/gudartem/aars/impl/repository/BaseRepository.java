package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.api.repository.TableDescriptorBuilder;
import org.gudartem.aars.db.model.HasId;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.Collection;

public abstract class BaseRepository<T extends HasId, ID extends Serializable> implements Repository<T, ID> {

    @Autowired
    protected TableDescriptorBuilder tableDescriptorBuilder;

    @Autowired
    @Qualifier("cimDslContext")
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

        }
    }

    protected <S extends T> S postUpdate(S entity) {
        return entity;
    }

    private boolean isNotManuallySetToNull(final Field field, final Record record, final Collection<String> nullFields) {
        return field.getValue(record) == null && !nullFields.contains(field.getUnqualifiedName().toString());
    }

    private T valueOrNull(Record record, Class<T> type) {
        return record == null ? null : record.into(type);
    }
}
