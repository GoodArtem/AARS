package org.gudartem.aars.impl.service.abstraction;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.db.model.HasId;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CRUDServiceImpl<Entity extends HasId<ID>, ID extends Serializable>
        implements CRUDService<Entity, ID> {

    protected abstract Repository<Entity, ID> getRepository();

    protected abstract ID generateNewId();

    @Override
    @Transactional(readOnly = true)
    public List<Entity> getAll() {
        List<Entity> result = getRepository().findAll();
        return postOperationEnrich(result);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> getByCondition(SearchRequestParams requestParams) {
        List<Entity> result = getRepository().findAll(requestParams);
        return postOperationEnrich(result, requestParams.getFetchPlan());
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
    public Collection<Entity> bulkCreate(Collection<Entity> entitiesToCreate) {
        if (entitiesToCreate == null) {
            throw new NullPointerException("List of creating items cannot be null.");
        }
        Collection<Entity> result = new ArrayList<>();
        for (Entity entity : entitiesToCreate) {
            result.add(create(entity));
        }
        return result;
    }

    @Override
    @Transactional
    public Entity create(Entity entityToCreate) {
        preCreate(entityToCreate);
        Entity result = getRepository().insert(entityToCreate);
        postCreateOrUpdate(result, entityToCreate);
        return postOperationEnrich(result);
    }

    @Override
    @Transactional
    public Entity patch(Entity entityToPatch) {
        preUpdate(entityToPatch);
        Entity result = getRepository().update(entityToPatch);
        postCreateOrUpdate(result, entityToPatch);
        return postOperationEnrich(result);
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

    @Transactional
    protected List<Entity> postOperationEnrich(List<Entity> entityList) {
        return postOperationEnrich(entityList, null);
    }

    @Transactional
    protected List<Entity> postOperationEnrich(List<Entity> entityList, Collection<String> fetchPlan) {
        if (entityList == null || entityList.isEmpty()) {
            return entityList;
        }
        for (Entity entity : entityList) {
            if (fetchPlan != null) {
                postOperationEnrich(entity, fetchPlan);
            } else {
                postOperationEnrich(entity);
            }
        }
        return entityList;
    }

    @Transactional
    protected Entity preCreate(Entity creatingEntity) {
        creatingEntity.setId(generateNewId());
        return creatingEntity;
    }

    @Transactional
    protected Entity preUpdate(Entity updatingEntity) {
        return updatingEntity;
    }

    @Transactional
    protected Entity postOperationEnrich(Entity entityToEnrich) {
        return postOperationEnrich(entityToEnrich, null);
    }

    @Transactional
    protected Entity postOperationEnrich(Entity entityToEnrich, Collection<String> fetchPlan) {
        return entityToEnrich;
    }

    @Transactional
    protected Entity postCreateOrUpdate(Entity resultEntity, Entity baseEntity) {
        return resultEntity;
    }
}
