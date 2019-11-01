package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;

public interface HasInventoryCardIdRepository<T extends HasId<ID>, ID extends Serializable>
        extends Repository<T, ID> {
    Collection<T> getAllByInvCardId(ID inventoryCardId);
}
