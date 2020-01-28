package org.gudartem.aars.impl.service.abstraction;

import org.gudartem.aars.db.model.HasId;

import java.util.UUID;

public abstract class CRUDServiceUUIDImpl<Entity extends HasId<UUID>> extends CRUDServiceImpl<Entity, UUID> {

    @Override
    protected UUID generateNewId() {
        return UUID.randomUUID();
    }
}
