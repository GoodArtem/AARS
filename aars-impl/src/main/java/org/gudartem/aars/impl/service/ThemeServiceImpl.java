package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.db.model.entity.Theme;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.THEME_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.THEME_SERVICE;

@Service(THEME_SERVICE)
public class ThemeServiceImpl extends CRUDServiceImpl<Theme, UUID> {

    private Repository repository;

    public ThemeServiceImpl(@Qualifier(THEME_REPOSITORY) Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository<Theme, UUID> getRepository() {
        return repository;
    }
}
