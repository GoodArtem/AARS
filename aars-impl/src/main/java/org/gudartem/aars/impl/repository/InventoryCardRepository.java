package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.HasDirectoryIdRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.INVENTORY_CARD_REPOSITORY;
import static org.gudartem.aars.db.jooq.Tables.INVENTORY_CARD;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.HasRevision.REVISION;
import static org.gudartem.aars.model.PojoFieldNames.HasThemeId.THEME_ID;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.ANNULLED_DATE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CARD_DATE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CARD_NAME;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CARD_TYPE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CIPHER;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.DIRECTORY_ID;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.INVENTORY_NUMBER;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.INVENTORY_NUMBER_SUF;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.MK;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.MKT;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.OKUFREG;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.OKUFSB;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.SHEET_COUNT;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.STATE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.TL;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.VOPTK;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.VTD;

@Repository(INVENTORY_CARD_REPOSITORY)
public class InventoryCardRepository
        extends BaseRepository<InventoryCard, UUID>
        implements HasDirectoryIdRepository<InventoryCard, UUID> {

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

    @Override
    public List<InventoryCard> getAllByThemeId(UUID themeId) {
        return findAll(INVENTORY_CARD.THEME_ID.eq(themeId));
    }

    @Override
    public List<InventoryCard> getAllByDirectoryId(UUID directoryId) {
        return findAll(INVENTORY_CARD.CARD_NAME.asc(), INVENTORY_CARD.DIRECTORY_ID.eq(directoryId));
    }
}
