package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;

public abstract class CRUDServiceImpl<Entity extends HasId<ID>, ID extends Serializable>
        implements CRUDService<Entity, ID> {

    protected abstract Repository<Entity, ID> getRepository();

    @Override
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
    public Entity getById(ID id) {
        Entity result = getRepository().findById(id);
        return postOperationEnrich(result);
    }

    @Override
    public Entity create(Entity entityToCreate) {
        Entity result = getRepository().insert(entityToCreate);
        return postOperationEnrich(result, entityToCreate);
    }

    @Override
    public Entity patch(Entity entityToPatch) {
        Entity result = getRepository().update(entityToPatch);
        return postOperationEnrich(result, entityToPatch);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    protected Entity postOperationEnrich(Entity entityToEnrich) {
        return postOperationEnrich(entityToEnrich, null);
    }

    protected Entity postOperationEnrich(Entity entityToEnrich, Entity baseEntity) {
        return entityToEnrich;
    }
}
