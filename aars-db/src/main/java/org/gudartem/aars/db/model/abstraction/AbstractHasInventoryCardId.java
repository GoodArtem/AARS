package org.gudartem.aars.db.model.abstraction;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static org.gudartem.aars.db.constants.ColumnName.HasInventoryCardId.INVENTORY_CARD_ID;
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;

@MappedSuperclass
public abstract class AbstractHasInventoryCardId<T> extends AbstractHasId<T> {

    @Column(name = INVENTORY_CARD_ID, columnDefinition = UUID_TYPE)
    private T inventoryCardId;

    public T getInventoryCardId() {
        return inventoryCardId;
    }

    public void setInventoryCardId(T inventoryCardId) {
        this.inventoryCardId = inventoryCardId;
    }
}
