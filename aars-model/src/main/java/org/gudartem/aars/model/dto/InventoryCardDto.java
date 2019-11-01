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

    private String inventoryNumber;

    private String inventoryNumberSuf;

    private String cardName;

    private String designation;

    private String cipher;

    private String sheetCount;

    private String tl;

    private String vtd;

    private String mk;

    private String mkt;

    private String voptk;

    private String okufsb;

    private String okufreg;

    private Integer cardType;

    private OffsetDateTime cardDate;

    private Integer state;

    private OffsetDateTime annulledDate;

    private UUID directoryId;

    private Set<FormatDto> formatSet;

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
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

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
        addNullField(TL, tl);
    }

    public String getVtd() {
        return vtd;
    }

    public void setVtd(String vtd) {
        this.vtd = vtd;
        addNullField(VTD, vtd);
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
        addNullField(MK, mk);
    }

    public String getMkt() {
        return mkt;
    }

    public void setMkt(String mkt) {
        this.mkt = mkt;
        addNullField(MKT, mkt);
    }

    public String getVoptk() {
        return voptk;
    }

    public void setVoptk(String voptk) {
        this.voptk = voptk;
        addNullField(VOPTK, voptk);
    }

    public String getOkufsb() {
        return okufsb;
    }

    public void setOkufsb(String okufsb) {
        this.okufsb = okufsb;
        addNullField(OKUFSB, okufsb);
    }

    public String getOkufreg() {
        return okufreg;
    }

    public void setOkufreg(String okufreg) {
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
