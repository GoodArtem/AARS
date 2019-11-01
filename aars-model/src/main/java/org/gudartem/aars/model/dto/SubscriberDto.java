package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseThemeIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.gudartem.aars.model.PojoFieldNames.Subscriber.ANNULLED;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.EX_NUMBER;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.SUBSCRIBER_NAME;
import static org.gudartem.aars.model.PojoFieldNames.Subscriber.SUBSCRIBE_DATE;

public class SubscriberDto extends BaseThemeIdDto<UUID> {

    private String subscriberName;

    private OffsetDateTime subscribeDate;

    private String exNumber;

    private String designation;

    private String annulled;

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
        addNullField(SUBSCRIBER_NAME, subscriberName);
    }

    public OffsetDateTime getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(OffsetDateTime subscribeDate) {
        this.subscribeDate = subscribeDate;
        addNullField(SUBSCRIBE_DATE, subscribeDate);
    }

    public String getExNumber() {
        return exNumber;
    }

    public void setExNumber(String exNumber) {
        this.exNumber = exNumber;
        addNullField(EX_NUMBER, exNumber);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
        addNullField(DESIGNATION, designation);
    }

    public String getAnnulled() {
        return annulled;
    }

    public void setAnnulled(String annulled) {
        this.annulled = annulled;
        addNullField(ANNULLED, annulled);
    }
}
