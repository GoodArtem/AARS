package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseThemeIdDto;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

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

    private Set<FormatDto> formatDtoSet;

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getInventoryNumberSuf() {
        return inventoryNumberSuf;
    }

    public void setInventoryNumberSuf(String inventoryNumberSuf) {
        this.inventoryNumberSuf = inventoryNumberSuf;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getSheetCount() {
        return sheetCount;
    }

    public void setSheetCount(String sheetCount) {
        this.sheetCount = sheetCount;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public String getVtd() {
        return vtd;
    }

    public void setVtd(String vtd) {
        this.vtd = vtd;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getMkt() {
        return mkt;
    }

    public void setMkt(String mkt) {
        this.mkt = mkt;
    }

    public String getVoptk() {
        return voptk;
    }

    public void setVoptk(String voptk) {
        this.voptk = voptk;
    }

    public String getOkufsb() {
        return okufsb;
    }

    public void setOkufsb(String okufsb) {
        this.okufsb = okufsb;
    }

    public String getOkufreg() {
        return okufreg;
    }

    public void setOkufreg(String okufreg) {
        this.okufreg = okufreg;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public OffsetDateTime getCardDate() {
        return cardDate;
    }

    public void setCardDate(OffsetDateTime cardDate) {
        this.cardDate = cardDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public OffsetDateTime getAnnulledDate() {
        return annulledDate;
    }

    public void setAnnulledDate(OffsetDateTime annulledDate) {
        this.annulledDate = annulledDate;
    }

    public UUID getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(UUID directoryId) {
        this.directoryId = directoryId;
    }

    public Set<FormatDto> getFormatDtoSet() {
        return formatDtoSet;
    }

    public void setFormatDtoSet(Set<FormatDto> formatDtoSet) {
        this.formatDtoSet = formatDtoSet;
    }
}
