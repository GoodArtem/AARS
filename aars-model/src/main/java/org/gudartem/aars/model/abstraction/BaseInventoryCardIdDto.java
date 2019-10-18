package org.gudartem.aars.model.abstraction;

public class BaseInventoryCardIdDto<T> extends BaseDto<T>  {
    private T inventoryCardId;

    public T getInventoryCardId() {
        return inventoryCardId;
    }

    public void setInventoryCardId(T inventoryCardId) {
        this.inventoryCardId = inventoryCardId;
    }
}
