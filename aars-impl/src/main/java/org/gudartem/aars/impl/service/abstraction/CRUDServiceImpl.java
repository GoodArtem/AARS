package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.db.model.HasId;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

public abstract class CRUDServiceImpl<Entity extends HasId<ID>, ID extends Serializable>
        implements CRUDService<Entity, ID> {

    protected abstract Repository<Entity, ID> getRepository();

    protected abstract ID generateNewId();

    @Override
    @Transactional(readOnly = true)
    public Collection<Entity> getAll() {
        Collection<Entity> result = getRepository().findAll();
        if (result != null && !result.isEmpty()) {
            for (Entity e : result) {
                postOperationEnrich(e);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Entity> getByCondition(SearchRequestParams requestParams) {
        Collection<Entity> result = getRepository().findAll(requestParams);
        if (result != null && !result.isEmpty()) {
            for (Entity e : result) {
                postOperationEnrich(e, requestParams.getFetchPlan());
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Entity getById(ID id) {
        Entity result = getRepository().findById(id);
        return postOperationEnrich(result);
    }

    @Override
    @Transactional(readOnly = true)
    public Entity getById(ID id, Collection<String> fetchPlan) {
        Entity result = getRepository().findById(id, fetchPlan);
        return postOperationEnrich(result, fetchPlan);
    }

    @Override
    @Transactional
    public Entity create(Entity entityToCreate) {
        entityToCreate.setId((ID) UUID.randomUUID());
        Entity result = getRepository().insert(entityToCreate);
        return postOperationEnrich(result, entityToCreate);
    }

    @Override
    @Transactional
    public Entity patch(Entity entityToPatch) {
        Entity result = getRepository().update(entityToPatch);
        return postOperationEnrich(result, entityToPatch);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    protected Entity postOperationEnrich(Entity entityToEnrich) {
        return postOperationEnrich(entityToEnrich, null, null);
    }

    protected Entity postOperationEnrich(Entity entityToEnrich, Collection<String> fetchPlan) {
        return postOperationEnrich(entityToEnrich, null, fetchPlan);
    }

    protected Entity postOperationEnrich(Entity entityToEnrich, Entity baseEntity) {
        return postOperationEnrich(entityToEnrich, baseEntity, null);
    }

    protected Entity postOperationEnrich(Entity entityToEnrich, Entity baseEntity, Collection<String> fetchPlan) {
        return entityToEnrich;
    }
}
