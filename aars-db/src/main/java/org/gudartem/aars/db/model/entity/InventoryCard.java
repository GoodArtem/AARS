package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasThemeId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.CardToFormat.FORMAT_ID;
import static org.gudartem.aars.db.constants.ColumnName.HasInventoryCardId.INVENTORY_CARD_ID;
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
import static org.gudartem.aars.db.constants.TableName.CARD_TO_FORMAT;
import static org.gudartem.aars.db.constants.TableName.INVENTORY_CARD;

@Entity
@Table(name = INVENTORY_CARD)
public class InventoryCard extends AbstractHasThemeId<UUID> {

    @Column(name = INVENTORY_NUMBER)
    private String inventoryNumber;

    @Column(name = INVENTORY_NUMBER_SUF)
    private String inventoryNumberSuf;

    @Column(name = CARD_NAME)
    private String cardName;

    @Column(name = DESIGNATION)
    private String designation;

    @Column(name = CIPHER)
    private String cipher;

    @Column(name = SHEET_COUNT)
    private String sheetCount;

    @Column(name = TL)
    private String tl;

    @Column(name = VTD)
    private String vtd;

    @Column(name = MK)
    private String mk;

    @Column(name = MKT)
    private String mkt;

    @Column(name = VOPTK)
    private String voptk;

    @Column(name = OKUFSB)
    private String okufsb;

    @Column(name = OKUFREG)
    private String okufreg;

    @Column(name = CARD_TYPE)
    private Integer cardType;

    @Column(name = CARD_DATE)
    private OffsetDateTime cardDate;

    @Column(name = STATE)
    private Integer state;

    @Column(name = ANNULLED_DATE)
    private OffsetDateTime annulledDate;

    @Column(name = DIRECTORY_ID)
    private UUID directoryId;

    @ManyToMany
    @JoinTable(name = CARD_TO_FORMAT,
            joinColumns = { @JoinColumn(name = INVENTORY_CARD_ID)},
            inverseJoinColumns = { @JoinColumn(name = FORMAT_ID)})
    private Set<Format> formatSet;

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

    public Set<Format> getFormatSet() {
        return formatSet;
    }

    public void setFormatSet(Set<Format> formatSet) {
        this.formatSet = formatSet;
    }
}
