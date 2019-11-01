package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;

public interface CRUDService<E extends HasId<ID>, ID extends Serializable> {

    Collection<E> getAll();

    E getById(ID id);

    E create(E entityToCreate);

    E patch(E entityToPatch);

    void delete(ID id);

    boolean existsById(ID id);
}
