package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseThemeIdDto;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.ANNULLED_DATE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CARD_DATE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CARD_NAME;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CARD_TYPE;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CIPHER;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.DIRECTORY_ID;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.FORMAT_SET;
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

public class InventoryCardDto extends BaseThemeIdDto<UUID> {

    private Integer inventoryNumber;

    private String inventoryNumberSuf;

    private String cardName;

    private String designation;

    private String cipher;

    private String sheetCount;

    private UUID tl;

    private UUID vtd;

    private UUID mk;

    private UUID mkt;

    private UUID voptk;

    private UUID okufsb;

    private UUID okufreg;

    private Integer cardType;

    private OffsetDateTime cardDate;

    private Integer state;

    private OffsetDateTime annulledDate;

    private UUID directoryId;

    private Set<FormatDto> formatSet;

    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
        addNullField(INVENTORY_NUMBER, inventoryNumber);
    }

    public String getInventoryNumberSuf() {
        return inventoryNumberSuf;
    }

    public void setInventoryNumberSuf(String inventoryNumberSuf) {
        this.inventoryNumberSuf = inventoryNumberSuf;
        addNullField(INVENTORY_NUMBER_SUF, inventoryNumberSuf);
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
        addNullField(CARD_NAME, cardName);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
        addNullField(DESIGNATION, designation);
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
        addNullField(CIPHER, cipher);
    }

    public String getSheetCount() {
        return sheetCount;
    }

    public void setSheetCount(String sheetCount) {
        this.sheetCount = sheetCount;
        addNullField(SHEET_COUNT, sheetCount);
    }

    public UUID getTl() {
        return tl;
    }

    public void setTl(UUID tl) {
        this.tl = tl;
        addNullField(TL, tl);
    }

    public UUID getVtd() {
        return vtd;
    }

    public void setVtd(UUID vtd) {
        this.vtd = vtd;
        addNullField(VTD, vtd);
    }

    public UUID getMk() {
        return mk;
    }

    public void setMk(UUID mk) {
        this.mk = mk;
        addNullField(MK, mk);
    }

    public UUID getMkt() {
        return mkt;
    }

    public void setMkt(UUID mkt) {
        this.mkt = mkt;
        addNullField(MKT, mkt);
    }

    public UUID getVoptk() {
        return voptk;
    }

    public void setVoptk(UUID voptk) {
        this.voptk = voptk;
        addNullField(VOPTK, voptk);
    }

    public UUID getOkufsb() {
        return okufsb;
    }

    public void setOkufsb(UUID okufsb) {
        this.okufsb = okufsb;
        addNullField(OKUFSB, okufsb);
    }

    public UUID getOkufreg() {
        return okufreg;
    }

    public void setOkufreg(UUID okufreg) {
        this.okufreg = okufreg;
        addNullField(OKUFREG, okufreg);
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
        addNullField(CARD_TYPE, cardType);
    }

    public OffsetDateTime getCardDate() {
        return cardDate;
    }

    public void setCardDate(OffsetDateTime cardDate) {
        this.cardDate = cardDate;
        addNullField(CARD_DATE, cardDate);
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
        addNullField(STATE, state);
    }

    public OffsetDateTime getAnnulledDate() {
        return annulledDate;
    }

    public void setAnnulledDate(OffsetDateTime annulledDate) {
        this.annulledDate = annulledDate;
        addNullField(ANNULLED_DATE, annulledDate);
    }

    public UUID getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(UUID directoryId) {
        this.directoryId = directoryId;
        addNullField(DIRECTORY_ID, directoryId);
    }

    public Set<FormatDto> getFormatSet() {
        return formatSet;
    }

    public void setFormatSet(Set<FormatDto> formatSet) {
        this.formatSet = formatSet;
        addNullField(FORMAT_SET, formatSet);
    }
}
