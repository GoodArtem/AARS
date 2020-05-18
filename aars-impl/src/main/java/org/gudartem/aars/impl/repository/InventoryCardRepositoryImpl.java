package org.gudartem.aars.impl.repository;

import org.gudartem.aars.api.repository.InventoryCardRepository;
import org.gudartem.aars.api.repository.TableDescriptor;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
import static org.jooq.impl.DSL.max;

@Repository(INVENTORY_CARD_REPOSITORY)
public class InventoryCardRepositoryImpl
        extends BaseRepository<InventoryCard, UUID>
        implements InventoryCardRepository {

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
        return getAllByThemeId(themeId, null);
    }

    @Override
    public List<InventoryCard> getAllByThemeId(UUID themeId, Integer cardType) {
        return findAll(INVENTORY_CARD.THEME_ID.eq(themeId),
                cardType != null
                        ? INVENTORY_CARD.CARD_TYPE.eq(cardType)
                        : INVENTORY_CARD.ID.isNotNull());
    }

    @Override
    public List<InventoryCard> getAllByDirectoryId(UUID directoryId) {
        return findAll(INVENTORY_CARD.CARD_NAME.asc(), INVENTORY_CARD.DIRECTORY_ID.eq(directoryId));
    }

    @Override
    public Integer getNextSequenceInventoryNumber(Integer cardType) {
        Integer lastNumber = getContext()
                .select(max(INVENTORY_CARD.INVENTORY_NUMBER))
                .from(INVENTORY_CARD)
                .where(INVENTORY_CARD.CARD_TYPE.eq(cardType))
                .fetchOneInto(Integer.class);
        return lastNumber == null ? 1 : lastNumber + 1;
    }

    @Override
    public Boolean getExistsInventoryCard(UUID id, Integer inventoryNumber, String inventoryNumberSuf, Integer cardType) {
        return 0 < getContext().fetchCount(INVENTORY_CARD,
                INVENTORY_CARD.CARD_TYPE.eq(cardType)
                        .and(INVENTORY_CARD.INVENTORY_NUMBER.eq(inventoryNumber))
                        .and(inventoryNumberSuf == null
                                ? INVENTORY_CARD.INVENTORY_NUMBER_SUF.isNull()
                                : INVENTORY_CARD.INVENTORY_NUMBER_SUF.eq(inventoryNumberSuf))
                        .and(id == null
                                ? INVENTORY_CARD.ID.isNotNull()
                                : INVENTORY_CARD.ID.notEqual(id)));
    }

    @Override
    public List<InventoryCard> getInventoryCardBySearchString(String searchString) {
        return getContext().selectFrom(getTableDescriptor().getTable())
                .where(INVENTORY_CARD.INVENTORY_NUMBER.like(searchString)
                        .or(INVENTORY_CARD.DESIGNATION.like(searchString))
                        .or(INVENTORY_CARD.CIPHER.like(searchString))
                        .or(INVENTORY_CARD.CARD_NAME.like(searchString))).orderBy(INVENTORY_CARD.CARD_NAME.asc())
                .fetchInto(getTableDescriptor().getEntityType());
    }
}
