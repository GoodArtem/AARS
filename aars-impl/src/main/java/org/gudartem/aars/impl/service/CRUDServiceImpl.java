package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;

public abstract class CRUDServiceImpl<Entity extends HasId, ID extends Serializable>
        implements CRUDService<Entity, ID> {

    protected abstract Repository<Entity, ID> getRepository();

    @Override
    public Collection<Entity> getAll() {
        return getRepository().findAll();
    }

    @Override
    public Entity getById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Entity create(Entity entityToCreate) {
        return getRepository().insert(entityToCreate);
    }

    @Override
    public Entity patch(Entity entityToPatch) {
        return getRepository().update(entityToPatch);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }
}
