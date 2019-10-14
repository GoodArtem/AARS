package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.SUBSCRIBER_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.SUBSCRIBER_SERVICE;

@Service(SUBSCRIBER_SERVICE)
public class SubscriberServiceImpl extends CRUDServiceImpl<Subscriber, UUID> {

    private Repository repository;

    public SubscriberServiceImpl(@Qualifier(SUBSCRIBER_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<Subscriber, UUID> getRepository() {
        return repository;
    }
}
