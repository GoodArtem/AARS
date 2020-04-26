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
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;
import static org.gudartem.aars.db.constants.TableName.CARD_TO_FORMAT;
import static org.gudartem.aars.db.constants.TableName.INVENTORY_CARD;

@Entity
@Table(name = INVENTORY_CARD)
public class InventoryCard extends AbstractHasThemeId<UUID> {

    @Column(name = INVENTORY_NUMBER)
    private Integer inventoryNumber;

    @Column(name = INVENTORY_NUMBER_SUF)
    private String inventoryNumberSuf;

    @Column(name = CARD_NAME)
    private String cardName;

    @Column(name = DESIGNATION)
    private String designation;

    @Column(name = CIPHER)
    private String cipher;

    @Column(name = SHEET_COUNT)
    private Integer sheetCount;

    @Column(name = TL, columnDefinition = UUID_TYPE)
    private UUID tl;

    @Column(name = VTD, columnDefinition = UUID_TYPE)
    private UUID vtd;

    @Column(name = MK, columnDefinition = UUID_TYPE)
    private UUID mk;

    @Column(name = MKT, columnDefinition = UUID_TYPE)
    private UUID mkt;

    @Column(name = VOPTK, columnDefinition = UUID_TYPE)
    private UUID voptk;

    @Column(name = OKUFSB, columnDefinition = UUID_TYPE)
    private UUID okufsb;

    @Column(name = OKUFREG, columnDefinition = UUID_TYPE)
    private UUID okufreg;

    @Column(name = CARD_TYPE)
    private Integer cardType;

    @Column(name = CARD_DATE)
    private OffsetDateTime cardDate;

    @Column(name = STATE)
    private Integer state;

    @Column(name = ANNULLED_DATE)
    private OffsetDateTime annulledDate;

    @Column(name = DIRECTORY_ID, columnDefinition = UUID_TYPE)
    private UUID directoryId;

    @ManyToMany
    @JoinTable(name = CARD_TO_FORMAT,
            joinColumns = { @JoinColumn(name = INVENTORY_CARD_ID)},
            inverseJoinColumns = { @JoinColumn(name = FORMAT_ID)})
    private Set<Format> formatSet;

    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
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

    public Integer getSheetCount() {
        return sheetCount;
    }

    public void setSheetCount(Integer sheetCount) {
        this.sheetCount = sheetCount;
    }

    public UUID getTl() {
        return tl;
    }

    public void setTl(UUID tl) {
        this.tl = tl;
    }

    public UUID getVtd() {
        return vtd;
    }

    public void setVtd(UUID vtd) {
        this.vtd = vtd;
    }

    public UUID getMk() {
        return mk;
    }

    public void setMk(UUID mk) {
        this.mk = mk;
    }

    public UUID getMkt() {
        return mkt;
    }

    public void setMkt(UUID mkt) {
        this.mkt = mkt;
    }

    public UUID getVoptk() {
        return voptk;
    }

    public void setVoptk(UUID voptk) {
        this.voptk = voptk;
    }

    public UUID getOkufsb() {
        return okufsb;
    }

    public void setOkufsb(UUID okufsb) {
        this.okufsb = okufsb;
    }

    public UUID getOkufreg() {
        return okufreg;
    }

    public void setOkufreg(UUID okufreg) {
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
