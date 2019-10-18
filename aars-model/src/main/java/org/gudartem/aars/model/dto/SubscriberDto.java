package org.gudartem.aars.model.dto;

import org.gudartem.aars.model.abstraction.BaseThemeIdDto;

import java.time.OffsetDateTime;
import java.util.UUID;

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
