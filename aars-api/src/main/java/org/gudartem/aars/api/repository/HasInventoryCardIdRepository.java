package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.List;

public interface HasInventoryCardIdRepository<T extends HasId<ID>, ID extends Serializable>
        extends Repository<T, ID> {
    List<T> getAllByInvCardId(ID inventoryCardId);
}
