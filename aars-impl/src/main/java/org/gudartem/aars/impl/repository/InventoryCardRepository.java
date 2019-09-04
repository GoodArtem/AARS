package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.HasId.ID;
import static org.gudartem.aars.db.constants.ColumnName.HasRevision.REVISION;
import static org.gudartem.aars.db.constants.ColumnName.HasThemeId.THEME_ID;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.ANNULLED_DATE;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.CARD_DATE;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.CARD_NAME;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.CARD_TYPE;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.CIPHER;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.DIRECTORY_ID;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.INVENTORY_NUMBER;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.INVENTORY_NUMBER_SUF;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.MK;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.MKT;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.OKUFREG;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.OKUFSB;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.SHEET_COUNT;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.STATE;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.TL;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.VOPTK;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.VTD;
import static org.gudartem.aars.db.jooq.Tables.INVENTORY_CARD;

@Repository("inventoryCardRepository")
public class InventoryCardRepository extends BaseRepository<InventoryCard, UUID> {

    private TableDescriptor tableDescriptor;

    @Override
    public TableDescriptor getTableDescriptor() {
        if (tableDescriptor != null) {
            return tableDescriptor;
        }

        Map<String, Field> mapping = new HashMap<>();
        mapping.put(ID, INVENTORY_CARD.ID);
        mapping.put(THEME_ID, INVENTORY_CARD.THEME_ID);
        mapping.put(REVISION, INVENTORY_CARD.REVISION);
        mapping.put(INVENTORY_NUMBER, INVENTORY_CARD.INVENTORY_NUMBER);
        mapping.put(INVENTORY_NUMBER_SUF, INVENTORY_CARD.INVENTORY_NUMBER_SUF);
        mapping.put(CARD_NAME, INVENTORY_CARD.CARD_NAME);
        mapping.put(DESIGNATION, INVENTORY_CARD.DESIGNATION);
        mapping.put(CIPHER, INVENTORY_CARD.CIPHER);
        mapping.put(SHEET_COUNT, INVENTORY_CARD.SHEET_COUNT);
        mapping.put(TL, INVENTORY_CARD.TL);
        mapping.put(VTD, INVENTORY_CARD.VTD);
        mapping.put(MK, INVENTORY_CARD.MK);
        mapping.put(MKT, INVENTORY_CARD.MKT);
        mapping.put(VOPTK, INVENTORY_CARD.VOPTK);
        mapping.put(OKUFSB, INVENTORY_CARD.OKUFSB);
        mapping.put(OKUFREG, INVENTORY_CARD.OKUFREG);
        mapping.put(CARD_TYPE, INVENTORY_CARD.CARD_TYPE);
        mapping.put(CARD_DATE, INVENTORY_CARD.CARD_DATE);
        mapping.put(STATE, INVENTORY_CARD.STATE);
        mapping.put(ANNULLED_DATE, INVENTORY_CARD.ANNULLED_DATE);
        mapping.put(DIRECTORY_ID, INVENTORY_CARD.DIRECTORY_ID);

        tableDescriptor = tableDescriptorBuilder.table(INVENTORY_CARD)
                .idField(INVENTORY_CARD.ID)
                .entityType(InventoryCard.class)
                .propertyFieldMapping(mapping)
                .build();

        return tableDescriptor;
    }
}
