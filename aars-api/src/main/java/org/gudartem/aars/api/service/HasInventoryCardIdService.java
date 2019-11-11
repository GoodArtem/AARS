package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface HasInventoryCardIdService<E extends HasId<ID>, ID extends Serializable>
        extends CRUDService<E, ID> {
    List<E> getAllByInvCardId(ID inventoryCardId);
}
