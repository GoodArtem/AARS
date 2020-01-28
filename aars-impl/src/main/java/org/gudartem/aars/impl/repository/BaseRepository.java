package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.api.repository.TableDescriptorBuilder;
import org.gudartem.aars.db.model.HasId;
import org.gudartem.aars.model.Pair;
import org.gudartem.aars.model.request.OrderDirection;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Select;
import org.jooq.SelectConditionStep;
import org.jooq.SelectLimitStep;
import org.jooq.SelectOrderByStep;
import org.jooq.SelectWhereStep;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.gudartem.aars.model.request.OperatorCondition.EQ;
import static org.gudartem.aars.model.request.OperatorCondition.GE;
import static org.gudartem.aars.model.request.OperatorCondition.GT;
import static org.gudartem.aars.model.request.OperatorCondition.IN;
import static org.gudartem.aars.model.request.OperatorCondition.LE;
import static org.gudartem.aars.model.request.OperatorCondition.LT;
import static org.gudartem.aars.model.request.OperatorCondition.NE;
import static org.gudartem.aars.model.request.OperatorCondition.NIN;

public abstract class BaseRepository<T extends HasId<ID>, ID extends Serializable> implements Repository<T, ID> {

    @Autowired
    protected TableDescriptorBuilder tableDescriptorBuilder;

    @Autowired
    @Qualifier("aarsDslContext")
    private DSLContext context;

    public abstract TableDescriptor getTableDescriptor();

    @Override
    public T findById(final ID id) {
        return findById(id, null);
    }

    @Override
    public T findById(final ID id, Collection<String> fetchPlan) {
        TableDescriptor descriptor = getTableDescriptor();
        Record fetch = getSelectWhereStep(descriptor, fetchPlan).where(descriptor.getIdField().eq(id)).fetchOne();
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
    public List<T> findAll() {
        TableDescriptor descriptor = getTableDescriptor();
        return context.selectFrom(descriptor.getTable()).fetchInto(descriptor.getEntityType());
    }

    @Override
    public List<T> findAll(Collection<? extends Condition> conditions) {
        TableDescriptor descriptor = getTableDescriptor();
        return context.selectFrom(descriptor.getTable())
                .where(conditions)
                .fetchInto(descriptor.getEntityType());
    }

    @Override
    public List<T> findAll(SearchRequestParams requestParams) {
        TableDescriptor descriptor = getTableDescriptor();
        Select select = getSelectWhereStep(descriptor, requestParams.getFetchPlan());
        Collection<? extends Condition> conditions = getConditions(requestParams);
        if (conditions != null && !conditions.isEmpty()) {
            select = ((SelectWhereStep) select).where(conditions);
        }
        Collection<? extends SortField> orderBy = getSortFields(requestParams);
        if (orderBy != null && !orderBy.isEmpty()) {
            select = ((SelectOrderByStep) select).orderBy(orderBy);
        }
        if (requestParams.getLimit() != null && requestParams.getOffset() != null) {
            select = ((SelectLimitStep) select).limit(requestParams.getLimit()).offset(requestParams.getOffset());
        }

        return select.fetchInto(descriptor.getEntityType());
    }

    @Override
    public List<T> findAll(Condition...conditions) {
        return findAll(null, conditions);
    }

    @Override
    public List<T> findAll(SortField orderBy, Condition...conditions) {
        TableDescriptor descriptor = getTableDescriptor();
        SelectConditionStep selectConditionStep = context.selectFrom(descriptor.getTable()).where(conditions);
        return (orderBy != null ? selectConditionStep.orderBy(orderBy) : selectConditionStep)
                .fetchInto(descriptor.getEntityType());
    }

    protected DSLContext getContext() {
        return context;
    }

    protected <S extends T> void preInsert(S entity, UpdatableRecord record) {
        setChangedFalseForNullFields(record);
    }

    protected <S extends T> S postInsert(S entity) {
        return entity;
    }

    protected <S extends T> void preUpdate(S entity, UpdatableRecord record) {
        if (entity.getNullFields() == null || entity.getNullFields().isEmpty()) {
            setChangedFalseForNullFields(record);
        }
        Collection<String> unqualifiedNameNullFields = pojoPropertyNameToUnqualified(entity.getNullFields());
        for (Field<?> field : record.fields()) {
            if (isNotManuallySetToNull(field, record, unqualifiedNameNullFields)) {
                record.changed(field, false);
            }
        }
    }

    protected <S extends T> S postUpdate(S entity) {
        return entity;
    }

    private void setChangedFalseForNullFields(UpdatableRecord record) {
        if (record.fields() != null) {
            for (Field<?> field : record.fields()) {
                if (field.getValue(record) == null) {
                    record.changed(field, false);
                }
            }
        }
    }

    private Collection<String> pojoPropertyNameToUnqualified(final Collection<String> properties) {
        Map<String, Field> mapping = getTableDescriptor().getPropertyFieldMapping();
        Collection<String> unqualifiedPropertyNames = new HashSet<>();
        for (String property : properties) {
            Field field = mapping.get(property);
            if (field != null) {
                unqualifiedPropertyNames.add(field.getUnqualifiedName().toString());
            }
        }
        return unqualifiedPropertyNames;
    }

    private boolean isNotManuallySetToNull(final Field field, final Record record, final Collection<String> nullFields) {
        return field.getValue(record) == null && !nullFields.contains(field.getUnqualifiedName().toString());
    }

    private T valueOrNull(Record record, Class<T> type) {
        return record == null ? null : record.into(type);
    }

    private SelectWhereStep getSelectWhereStep(TableDescriptor descriptor, Collection<String> fetchPlan) {
        if (fetchPlan == null || fetchPlan.isEmpty()) {
            return context.selectFrom(descriptor.getTable());
        }
        Collection<Field> fields = new ArrayList<>();
        for (String pojoFieldName : fetchPlan) {
            Field field = descriptor.getPropertyFieldMapping().get(pojoFieldName);
            if (field != null) {
                fields.add(field);
            }
        }
        return context.select(fields).from(descriptor.getTable());
    }

    private Collection<? extends Condition> getConditions(SearchRequestParams requestParams) {
        Map<String, Pair<String, Object>> searchCondition = requestParams.getSearchCondition();
        if (searchCondition == null || searchCondition.isEmpty()) {
            return null;
        }
        List<? extends Condition> result = new ArrayList<>();
        for (Map.Entry<String, Pair<String, Object>> entry : searchCondition.entrySet()) {
            Field field = getTableDescriptor().getPropertyFieldMapping().get(entry.getKey());
            if (field != null) {
                result.add(getCondition(field, entry.getValue().getLeft(), entry.getValue().getRight()));
            }
        }
        return result.isEmpty() ? null : result;
    }

    private <C extends Condition> C getCondition(Field field, String operatorCondition, Object value) {
        switch (operatorCondition) {
            case EQ: return (C) field.eq(value);
            case NE: return (C) field.ne(value);
            case LT: return (C) field.lt(value);
            case LE: return (C) field.le(value);
            case GT: return (C) field.gt(value);
            case GE: return (C) field.ge(value);
            case IN: return (C) field.in(value);
            case NIN: return (C) field.notIn(value);
        }
        throw new IllegalArgumentException("Not supported operator condition");
    }

    private Collection<? extends SortField> getSortFields(SearchRequestParams requestParams) {
        List<Pair<String, String>> orderBy = requestParams.getOrderBy();
        if (orderBy == null || orderBy.isEmpty()) {
            return null;
        }
        List<SortField> result = new ArrayList<>();
        for (Pair<String, String> pairOrderBy : orderBy) {
            Field field = getTableDescriptor().getPropertyFieldMapping().get(pairOrderBy.getLeft());
            if (field != null) {
                SortField sortField;
                switch (pairOrderBy.getRight()) {
                    case OrderDirection.ASC:
                        sortField = field.sort(SortOrder.ASC);
                        break;
                    case OrderDirection.DESC:
                        sortField = field.sort(SortOrder.DESC);
                        break;
                    default: sortField = field.sort(SortOrder.DEFAULT);
                }
                result.add(sortField);
            }
        }
        return result.isEmpty() ? null : result;
    }
}
