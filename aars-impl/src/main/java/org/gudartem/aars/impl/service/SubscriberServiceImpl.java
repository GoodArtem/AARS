package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasThemeIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasThemeIdService;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.SUBSCRIBER_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.SUBSCRIBER_SERVICE;

@Service(SUBSCRIBER_SERVICE)
public class SubscriberServiceImpl
        extends CRUDServiceImpl<Subscriber, UUID>
        implements HasThemeIdService<Subscriber, UUID> {

    private HasThemeIdRepository repository;

    public SubscriberServiceImpl(@Qualifier(SUBSCRIBER_REPOSITORY) HasThemeIdRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Subscriber> getAllByThemeId(UUID themeId) {
        return repository.getAllByThemeId(themeId);
    }

    @Override
    protected Repository<Subscriber, UUID> getRepository() {
        return repository;
    }
}
