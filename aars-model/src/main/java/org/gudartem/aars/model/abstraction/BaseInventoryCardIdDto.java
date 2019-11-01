package org.gudartem.aars.model.abstraction;

import static org.gudartem.aars.model.PojoFieldNames.HasInventoryCardId.INVENTORY_CARD_ID;

public class BaseInventoryCardIdDto<T> extends BaseDto<T>  {
    private T inventoryCardId;

    public T getInventoryCardId() {
        return inventoryCardId;
    }

    public void setInventoryCardId(T inventoryCardId) {
        this.inventoryCardId = inventoryCardId;
        addNullField(INVENTORY_CARD_ID, inventoryCardId);
    }
}
