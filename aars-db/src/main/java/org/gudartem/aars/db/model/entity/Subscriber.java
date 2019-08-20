package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasThemeId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Subscriber.ANNULLED;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.DESIGNATION;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.EX_NUMBER;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.SUBSCRIBER_NAME;
import static org.gudartem.aars.db.constants.ColumnName.Subscriber.SUBSCRIBE_DATE;
import static org.gudartem.aars.db.constants.TableName.SUBSCRIBER;

@Entity
@Table(name = SUBSCRIBER)
public class Subscriber extends AbstractHasThemeId<UUID> {

    @Column(name = SUBSCRIBER_NAME)
    private String subscriberName;

    @Column(name = SUBSCRIBE_DATE)
    private OffsetDateTime subscribeDate;

    @Column(name = EX_NUMBER)
    private String exNumber;

    @Column(name = DESIGNATION)
    private String designation;

    @Column(name = ANNULLED)
    private String annulled;

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public OffsetDateTime getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(OffsetDateTime subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getExNumber() {
        return exNumber;
    }

    public void setExNumber(String exNumber) {
        this.exNumber = exNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAnnulled() {
        return annulled;
    }

    public void setAnnulled(String annulled) {
        this.annulled = annulled;
    }
}
